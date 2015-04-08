package com.groupTen.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.groupTen.model.*;

public class WeatherDataDao extends JdbcDaoSupport  {

	public void insertData(List<Weather> dataList){
		 String sql = "INSERT INTO T_OC_ORIGINAL_NEWS" +
	                "(ORIGINAL_NEWS_UID,MEDIA_NAME,MEDIA_TYPE,LANGUAGE,URL,CHARSET,CHANNEL_TYPE,TITLE,KEYWORDS," +
	                "PUB_TIME,SOURCE,SOURCE_URL,AUTHOR,BROWSE_CNT,NEWS_CONTENT,COMMENTADD,EXPRESSION_LEVEL,RELATIVE_LINK," +
	                "COLLECTION_UID,CLUE_TYPE,CREATE_DATETIME,CREATE_BY) " +
	                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         
	        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){
	            @Override
	            public int getBatchSize() {
	                return dataList.size();
	            }
	            @Override
	            public void setValues(PreparedStatement ps, int i) throws SQLException {
	                Weather weather = dataList.get(i);
	                ps.setInt(1, weather.getMonth());
	                ps.setInt(2, weather.getYear());
	                /*ps.setString(3, newsEntity.getMediaType());
	                ps.setString(4, newsEntity.getLanguage());
	                ps.setString(5, newsEntity.getUrl());
	                ps.setString(6, newsEntity.getCharset());
	                ps.setString(7, newsEntity.getChannelType());
	                ps.setString(8, newsEntity.getTitle());
	                ps.setString(9, newsEntity.getKeywords());
	                ps.setString(10, newsEntity.getPubTime());
	                ps.setString(11, newsEntity.getSource());
	                ps.setString(12, newsEntity.getSourceUrl());
	                ps.setString(13, newsEntity.getAuthor());
	                ps.setLong(14, newsEntity.getBrowseCnt());
	       
	                //lobHandler.getLobCreator().setClobAsString(ps, 15, newsEntity.getNewsContent());
	                ps.setString(16, newsEntity.getCommentadd());
	                ps.setString(17, newsEntity.getExpressionLevel());
	                ps.setString(18, newsEntity.getRelativeLink());
	                ps.setString(19, newsEntity.getCollectionUid());
	                ps.setString(20, newsEntity.getClueType());
	                ps.setTimestamp(21, new Timestamp(newsEntity.getCreateDatetime().getTime()));
	                ps.setString(22, newsEntity.getCreateBy());*/
	            }
				
	             
	        } );
	} 
}
