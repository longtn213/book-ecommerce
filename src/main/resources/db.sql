INSERT INTO categories (name, slug, parent_id)
VALUES ('Văn học', 'van-hoc', NULL),
       ('Kinh tế', 'kinh-te', NULL),
       ('Tâm lý - Kỹ năng sống', 'tam-ly-ky-nang', NULL),
       ('Thiếu nhi', 'thieu-nhi', NULL),
       ('Công nghệ - Khoa học', 'cong-nghe-khoa-hoc', NULL),
       ('Tiểu thuyết', 'tieu-thuyet', 1),
       ('Truyện ngắn', 'truyen-ngan', 1),
       ('Kinh doanh', 'kinh-doanh', 2),
       ('Tài chính', 'tai-chinh', 2),
       ('Kỹ năng sống', 'ky-nang-song', 3),
       ('Tâm lý học', 'tam-ly-hoc', 3),
       ('Truyện tranh', 'truyen-tranh', 4),
       ('Sách học tập', 'sach-hoc-tap', 4),
       ('Lập trình', 'lap-trinh', 5),
       ('Khoa học tự nhiên', 'khoa-hoc-tu-nhien', 5),
       ('Tiểu thuyết lịch sử', 'tieu-thuyet-lich-su', 6),
       ('Tiểu thuyết trinh thám', 'tieu-thuyet-trinh-tham', 6),
       ('Kinh doanh khởi nghiệp', 'kinh-doanh-khoi-nghiep', 8),
       ('Đầu tư tài chính', 'dau-tu-tai-chinh', 9),
       ('Kỹ năng giao tiếp', 'ky-nang-giao-tiep', 10);
INSERT INTO publishers (name, address, contact_email, contact_phone)
VALUES ('NXB Trẻ', 'Hồ Chí Minh', 'contact@nxbtremedia.vn', '028-3832-1234'),
       ('NXB Kim Đồng', 'Hà Nội', 'info@kimdong.vn', '024-3843-6789'),
       ('NXB Tổng hợp TP.HCM', 'TP.HCM', 'support@nxbtonghop.vn', '028-3899-1122'),
       ('NXB Lao Động', 'Hà Nội', 'info@laodong.vn', '024-3923-7890'),
       ('NXB Thế Giới', 'Hà Nội', 'info@thegioibooks.vn', '024-3733-5555');
INSERT INTO authors (name, bio, avatar_url)
VALUES ('Nguyễn Nhật Ánh', 'Nhà văn nổi tiếng với các tác phẩm tuổi học trò.','/images/authors/nguyen-nhat-anh.jpg'),
       ('Paulo Coelho', 'Tác giả Brazil, nổi tiếng với “Nhà Giả Kim”.', '/images/authors/paulo-coelho.jpg'),
       ('Haruki Murakami', 'Nhà văn Nhật Bản đương đại.', '/images/authors/haruki-murakami.jpg'),
       ('J.K. Rowling', 'Tác giả bộ truyện Harry Potter.', '/images/authors/jk-rowling.jpg'),
       ('Dale Carnegie', 'Tác giả người Mỹ, chuyên về kỹ năng sống.', '/images/authors/dale-carnegie.jpg'),
       ('Napoleon Hill', 'Tác giả kinh điển về phát triển bản thân.', '/images/authors/napoleon-hill.jpg'),
       ('Stephen Hawking', 'Nhà vật lý nổi tiếng, tác giả “Lược sử thời gian”.','/images/authors/stephen-hawking.jpg'),
       ('Trần Đăng Khoa', 'Nhà thơ, nhà văn Việt Nam.', '/images/authors/tran-dang-khoa.jpg'),
       ('Yuval Noah Harari', 'Nhà sử học người Israel, tác giả “Sapiens”.', '/images/authors/yuval-noah-harari.jpg'),
       ( 'Malcolm Gladwell', 'Nhà báo và tác giả người Canada.', '/images/authors/malcolm-gladwell.jpg');

DELIMITER $$

CREATE PROCEDURE generate_books()
BEGIN
    DECLARE i INT DEFAULT 4;
    WHILE i <= 500 DO
            INSERT INTO books (
                title, slug, isbn, description, price, cost_price, stock_quantity,
                pages, language, publish_year, status, publisher_id, created_at, updated_at
            )
            VALUES (
                       CONCAT('Sách hay số ', i),
                       CONCAT('sach-hay-so-', i),
                       CONCAT('ISBN-', LPAD(i, 5, '0')),
                       'Một cuốn sách được tạo ngẫu nhiên để test hệ thống.',
                       ROUND(RAND() * 200000 + 30000, 0),     -- Giá bán
                       ROUND(RAND() * 100000 + 20000, 0),     -- Giá nhập
                       FLOOR(RAND() * 100 + 10),
                       FLOOR(RAND() * 400 + 100),
                       ELT(FLOOR(RAND() * 3) + 1, 'Tiếng Việt', 'English', 'Japanese'),
                       FLOOR(RAND() * 24 + 2000),
                       ELT(FLOOR(RAND() * 3) + 1, 'ACTIVE', 'OUT_OF_STOCK', 'HIDDEN'),
                       FLOOR(RAND() * 5 + 1),   -- publisher_id ngẫu nhiên 1-5
                       NOW(),
                       NOW()
                   );
            SET i = i + 1;
END WHILE;
END $$

DELIMITER ;

CALL generate_books();
DROP PROCEDURE generate_books;

INSERT INTO book_author (book_id, author_id)
SELECT b.id, FLOOR(1 + RAND() * 10)
FROM books b;

INSERT INTO book_category (book_id, category_id)
SELECT b.id, FLOOR(1 + RAND() * 20)
FROM books b;