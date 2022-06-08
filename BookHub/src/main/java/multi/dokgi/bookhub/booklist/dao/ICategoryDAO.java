package multi.dokgi.bookhub.booklist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.dokgi.bookhub.booklist.dto.CategoryDTO;

@Mapper
@Repository("categorydao")
public interface ICategoryDAO {
	public List<CategoryDTO> getCategoryList(String mall);
}
