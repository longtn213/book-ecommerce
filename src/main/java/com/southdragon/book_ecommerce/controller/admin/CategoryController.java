package com.southdragon.book_ecommerce.controller.admin;

import com.southdragon.book_ecommerce.dto.admin.CategoryDto;
import com.southdragon.book_ecommerce.dto.admin.CategoryRequest;
import com.southdragon.book_ecommerce.dto.base.ApiResponse;
import com.southdragon.book_ecommerce.service.admin.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.southdragon.book_ecommerce.constant.MessageConstant.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 🔹 Lấy danh sách thể loại
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success(COMMON_GET_SUCCESS,categoryService.findAll()));
    }

    // 🔹 Thêm thể loại mới
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody CategoryRequest category) {
        CategoryDto savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(ApiResponse.success(COMMON_CREATE_SUCCESS,savedCategory));
    }

    // 🔹 Cập nhật thể loại
    @PutMapping("")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@RequestBody CategoryRequest request) {
        CategoryDto updatedCategory = categoryService.updateCategory(request);
        return ResponseEntity.ok(ApiResponse.success(COMMON_UPDATE_SUCCESS,updatedCategory));
    }

    // 🔹 Xóa thể loại
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(ApiResponse.success(COMMON_DELETE_SUCCESS));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
