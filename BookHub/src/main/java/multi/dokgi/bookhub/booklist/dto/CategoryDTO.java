package multi.dokgi.bookhub.booklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {
	private int cid;
	private String mall;
	private String categoryName;
	private String oneDepth;
}
