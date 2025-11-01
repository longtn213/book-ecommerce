package com.southdragon.book_ecommerce.service.admin;

import com.southdragon.book_ecommerce.dto.admin.CategoryDto;
import com.southdragon.book_ecommerce.dto.admin.CategoryRequest;
import com.southdragon.book_ecommerce.model.Book;
import com.southdragon.book_ecommerce.model.Category;
import com.southdragon.book_ecommerce.repository.BookRepository;
import com.southdragon.book_ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.southdragon.book_ecommerce.constant.MessageConstant.COMMON_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryDto::fromEntity).toList();
    }

    public CategoryDto createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        if (request.getCategoryParentId() != null) {
            Category parent = categoryRepository.findById(request.getCategoryParentId())
                    .orElseThrow(() -> new RuntimeException(COMMON_DATA_NOT_FOUND));
            category.setParent(parent);
        }
        Category savedCategory = categoryRepository.save(category);
        return CategoryDto.fromEntity(savedCategory);
    }

    public CategoryDto updateCategory(CategoryRequest request) {
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException(COMMON_DATA_NOT_FOUND));
        if (category.getName() != null && !request.getName().equals(category.getName())) {
            category.setName(request.getName());
        }
        if (request.getSlug() != null && !request.getSlug().equals(category.getSlug())) {
            category.setSlug(request.getSlug());
        }
        if (request.getCategoryParentId() != null) {
            Category parent = categoryRepository.findById(request.getCategoryParentId())
                    .orElseThrow(() -> new RuntimeException(COMMON_DATA_NOT_FOUND));
            category.setParent(parent);
        }
        Category updatedCategory = categoryRepository.save(category);
        return CategoryDto.fromEntity(updatedCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Thể loại không tồn tại"));

        // ✅ Kiểm tra nếu có category con
        List<Category> subCategories = categoryRepository.findByParentId(categoryId);
        if (!subCategories.isEmpty()) {
            throw new RuntimeException("Không thể xóa thể loại vì đang có danh mục con.");
        }

        // ✅ Nếu bạn có Book liên kết với Category (ManyToMany)
        List<Book> books = bookRepository.findByCategories_Id(categoryId);
        if (!books.isEmpty()) {
            throw new RuntimeException("Không thể xóa thể loại vì đang được sử dụng bởi sách.");
        }

        categoryRepository.delete(category);
    }

}
