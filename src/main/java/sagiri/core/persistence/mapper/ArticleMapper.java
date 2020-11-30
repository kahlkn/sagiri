package sagiri.core.persistence.mapper;

import sagiri.core.persistence.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ArticleMapper.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Mapper
public interface ArticleMapper {

    /* (Start) This will be covered, please do not modify. */
    /**
     * Insert.
     * @param record The object to insert
     * @return Number of rows effected
     */
    int insert(Article record);

    /**
     * Insert batch.
     * @param recordList A list of records to insert
     * @return Number of rows effected
     */
    int insertBatch(@Param("recordList") List<Article> recordList);

    /**
     * Insert selective.
     * @param record The object to insert
     * @return Number of rows effected
     */
    int insertSelective(Article record);

    /**
     * Delete by primary key.
     * @param id Primary key
     * @param updaterId Current operator
     * @return Number of rows effected
     */
    int deleteByPrimaryKey(@Param("id") Long id, @Param("updaterId") String updaterId);

    /**
     * Delete by primary key list.
     * @param idList Primary key list
     * @param updaterId Current operator
     * @return Number of rows effected
     */
    int deleteByPrimaryKeyList(@Param("idList") List<Long> idList, @Param("updaterId") String updaterId);

    /**
     * Delete selective.
     * @param record Delete conditions
     * @return Number of rows effected
     */
    int deleteSelective(Article record);

    /**
     * Update by primary key.
     * @param record Content to be updated
     * @return Number of rows effected
     */
    int updateByPrimaryKey(Article record);

    /**
     * Update by primary key selective.
     * @param record Content to be updated
     * @return Number of rows effected
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * Conditional counting.
     * @param record Query condition
     * @return Count result
     */
    int countSelective(Article record);

    /**
     * Query by primary key.
     * @param id Primary key
     * @return Query result
     */
    Article queryByPrimaryKey(@Param("id") Long id);

    /**
     * Query by primary key list.
     * @param idList Primary key list
     * @return Query result list
     */
    List<Article> queryByPrimaryKeyList(@Param("idList") List<Long> idList);

    /**
     * Find one.
     * @param record Query condition
     * @return Query result
     */
    Article findOne(Article record);

    /**
     * Query selective.
     * @param record Query condition
     * @return Query result list
     */
    List<Article> querySelective(Article record);
    /* (End) This will be covered, please do not modify. */
    /* Generated by artoria-extend in 2020-11-30T16:17:50.292+0800. */

}
