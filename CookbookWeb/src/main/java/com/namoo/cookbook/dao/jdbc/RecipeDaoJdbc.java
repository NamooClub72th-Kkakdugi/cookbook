package com.namoo.cookbook.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.namoo.cookbook.dao.RecipeDao;
import com.namoo.cookbook.domain.ImageFile;
import com.namoo.cookbook.domain.Recipe;
import com.namoo.cookbook.shared.exception.CookbookRuntimeException;

@Repository
public class RecipeDaoJdbc implements RecipeDao{
	//
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Recipe> readAllRecipes() {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Recipe> recipes = new ArrayList<Recipe>();
		try {
			conn = dataSource.getConnection();
//			String sql = "SELECT recipe_nm, ingredients, procedures FROM recipe_tb";
			// 위 또는 아래 둘 중 한 방식으로 쓰면 된다.
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT recipe_nm, ingredients, procedures, img_type, img_file ");
			sb.append("FROM recipe_tb");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			
			rs = pstmt.executeQuery();
			
			while (rs.next() ) {
				recipes.add(mapToRecipe(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CookbookRuntimeException("모든 조립법 목록조회 중 오류", e);
		} finally {
			closeQueitly(rs, pstmt, conn);
		}
		return recipes;
	}

	@Override
	public Recipe readRecipe(String recipeName) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Recipe recipe = null;
		try {
			conn = dataSource.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT recipe_nm, ingredients, procedures, img_type, img_file ");
			sb.append("FROM recipe_tb ");
			sb.append("WHERE recipe_nm = ?");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			pstmt.setString(1,  recipeName);
			
			rs = pstmt.executeQuery();
			
			if (rs.next() ) {
				recipe = mapToRecipe(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CookbookRuntimeException("조립법 조회 중 오류", e);
		} finally {
			closeQueitly(rs, pstmt, conn);
		}
		return recipe;
	}

	@Override
	public void insertRecipe(Recipe recipe) {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO recipe_tb(recipe_nm, ingredients, procedures, img_type, img_file) ");
			sb.append("VALUES (?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			pstmt.setString(1, recipe.getName());
			pstmt.setString(2, recipe.getIngredients());
			pstmt.setString(3, recipe.getProcedure());
			
			ImageFile recipeImage = recipe.getRecipeImage();
			if (recipe.getRecipeImage() != null) {
				pstmt.setString(4, recipeImage.getContentType());
				pstmt.setString(5, recipeImage.getFileName());
			} else {
				pstmt.setString(4, "");
				pstmt.setString(5, "");
			}
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CookbookRuntimeException("조립법 추가 중 오류", e);
		} finally {
			closeQueitly(pstmt, conn);
		}
	}

	@Override
	public void updateRecipe(Recipe recipe) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE recipe_tb SET ingredients = ?, procedures =  ?, image_type = ?, image_file = ? ");
			sb.append("WHERE recipe_nm = ?");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			pstmt.setString(1, recipe.getIngredients());
			pstmt.setString(2, recipe.getProcedure());
			
			ImageFile recipeImage = recipe.getRecipeImage();
			if (recipe.getRecipeImage() != null) {
				pstmt.setString(3, recipeImage.getContentType());
				pstmt.setString(4, recipeImage.getFileName());
			} else {
				pstmt.setString(3, "");
				pstmt.setString(4, "");
			}
			pstmt.setString(5, recipe.getName());
			
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CookbookRuntimeException("조립법 수정 중 오류", e);
		} finally {
			closeQueitly(pstmt, conn);
		}
	}

	@Override
	public void deleteRecipe(String recipeName) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("DELETE FROM recipe_tb ");
			sb.append("WHERE recipe_nm = ?");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			pstmt.setString(1, recipeName);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CookbookRuntimeException("조립법 삭제 중 오류", e);
		} finally {
			closeQueitly(pstmt, conn);
		}
	}

	//-------------------------------------------------------------------------------------------
	// private method
	
	/**
	 * ResultSet을 Recipe객체로 변환한다.
	 * 
	 * @param rs 조회결과
	 * @return {@link Recipe}
	 * @throws SQLException
	 */
	private Recipe mapToRecipe(ResultSet rs) throws SQLException {
		//
		String recipeName = rs.getString("recipe_nm");
		String ingredients = rs.getString("ingredients");
		String procedure = rs.getString("procedures");
		
		Recipe recipe = new Recipe(recipeName, ingredients, procedure);
		
		String imageType = rs.getString("img_type");
		String imageFile = rs.getString("img_file");
		if ( !StringUtils.isEmpty(imageType) && !StringUtils.isEmpty(imageFile)) {
			ImageFile recipeImage = new ImageFile(imageType, imageFile);
			recipe.setRecipeImage(recipeImage);
		}
		return recipe;
	}

	/**
	 * DB관련 자원을 종료한다.
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	private void closeQueitly(AutoCloseable ...autoCloseables) {
		//
		for (AutoCloseable closeable : autoCloseables) {
			if(closeable != null) try { closeable.close(); } catch (Exception e) {}
		}
	}
	

}
