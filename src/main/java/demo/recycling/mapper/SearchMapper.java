package demo.recycling.mapper;

import demo.recycling.dto.SearchDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {

    @Select("SELECT * FROM recycling.detailsearch where d_no in (SELECT TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(recycling.SearchResults.s_no, ',',NUMBERS.N), ',', -1)) AS VALUE FROM(SELECT 1 N UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5)NUMBERS INNER JOIN recycling.SearchResults ON char_length(recycling.SearchResults.s_no)-char_length(REPLACE(recycling.SearchResults.s_no, ',','')) >= NUMBERS.N-1 WHERE r_name like CONCAT('%', #{keyword}, '%'));")
    public List<SearchDetail> selectDetailSearch(String keyword) throws Exception;

}