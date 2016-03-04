package uk.gov.ofwat.fountain.dao

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp
import java.sql.Types;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;

class SearchDaoImpl extends SimpleJdbcDaoSupport implements SearchDao{

			ssd.id = rs.getLong("id")
			ssd.sortByDate = rs.getBoolean("sortByDate")
			ssd.sortOrder = rs.getString("sortOrder")
			ssd.searchType = rs.getString("searchType")
	Boolean checkIfSearchExistsInHistory(SaveSearchDto saveSearchDto){
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE query = ? and user = ? and myDataOnly = ? and includeDeleted = ? and favorite = false"
		SaveSearchDto savedSearch
		try{
			savedSearch = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, saveSearchDto.searchString, saveSearchDto.user, saveSearchDto.myDataOnly, saveSearchDto.includeDeleted)
			return true
		}catch(Exception e){
			println e
			println "Couldn't find existing search." 
		}
		return false
	}


		if(!checkIfSearchExistsInHistory(dto)){
			String sql = "INSERT INTO " + TABLE_NAME + " (query, user, searchName, myDataOnly, includeDeleted, createdAt, sortByDate, sortOrder, searchType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
			su.declareParameter(new SqlParameter(Types.BOOLEAN)) // sortByDate
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // sortOrder
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // searchType
			dto.createdAt = new Date()
			updateSearchDtoCreated(dto)
		}
		return dto
	
	@Override
	def saveSearchFavorite(SaveSearchDto dto) {
		String sql = "INSERT INTO " + TABLE_NAME + " (query, user, searchName, myDataOnly, includeDeleted, createdAt, favorite, sortByDate, sortOrder, searchType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // sortByDate
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // sortOrder
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // searchType

	@Override
	def getSearchHistoryDtos(int maxToReturn, String user) {
		//String parsedUser = user.replaceAll("\\","\\\\")
		//String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user='${parsedUser}' AND favorite = false ORDER BY createdAt DESC  limit ${maxToReturn}";
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user= ? AND favorite = false ORDER BY createdAt DESC  limit ?";
			println (ex)
	}
	
	@Override
	def getSearchFavoriteDtos(int maxToReturn, String user) {
		//String parsedUser = user.replaceAll("\\","\\\\")
		//String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user='${parsedUser}' AND favorite = true ORDER BY createdAt DESC limit ${maxToReturn}";
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user= ? AND favorite = true ORDER BY createdAt DESC limit ?";
		List<SaveSearchDto> savedSearches = [];
		try{
			savedSearches = getSimpleJdbcTemplate().query(sql, ROW_MAPPER, user, maxToReturn);
		}
		catch(EmptyResultDataAccessException ex){
			// Its OK to find no data.
			println (ex)
		}
		return savedSearches;
	}
	
	def updateSearchDtoCreated(SaveSearchDto saveSearchDto){
		String sql = "UPDATE " + TABLE_NAME  + " SET createdAt = ? WHERE id = ?"
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // dateCreated
		su.declareParameter(new SqlParameter(Types.BIGINT)); // searchId
		Object[] parameters = [new Timestamp(saveSearchDto.getCreatedAt().getTime()), saveSearchDto.id]
		su.update(parameters)
	}

	@Override
	def getSearchHistoryDtos(int maxToReturn) {
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE favorite = false ORDER BY createdAt DESC limit ${maxToReturn}";
			println (ex)
		
		def savedSearch
			savedSearch = getSimpleJdbcTemplate().queryForObject(sql, ROW_MAPPER, id)
		}catch(Exception e){
			println "Couldn't find search with id " + id.toString()
		}
		return savedSearch
	}
		su.declareParameter(new SqlParameter(Types.BIGINT)); // searchId
	}
}