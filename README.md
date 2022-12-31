# 簡易電商網站作品集

## 使用軟體

1. IntelliJ IDEA 2022.2.3
2. MySQL 8.0.22

## 使用技術

1. Java 11.0.16
2. Spring Boot
3. RESTful API
4. Thymeleaf
5. Bootstrap 5
6. HTML
7. CSS
8. Javascript
9. Jquery 3.6.1
10. AJAX
11. 購物車 Cookie
12. Unit Test
13. AWS

## 作品集說明

### 後端相關：

後端頁面使用了 Spring Boot 框架來撰寫代碼，其中還設計了 RESTful API 方便前端串接後端程式，同時還使用了 Thymeleaf 模板方便前端進入特定頁面

後端頁面採用了經典的 Controller-Service-Dao 三層結構來呈現，Controller 層負責利用 RESTful API 和前端溝通，Service 層處理業務上的邏輯，Dao 層則專心利用 SQL 語句和資料庫溝通

為了讓前端獲取後端的參數，這裡還使用了 Session 的技術，方便前端利用 Thymeleaf 語法來獲取後端的數據

其他的資料夾介紹如下：

dto-->這裡用來放置請求參數相關的檔案

mapper-->這裡用來放置從資料庫獲得的資料所需要依附的 Java 物件

model-->這裡用來放置 User、Product 等等的 Java 物件

util-->這裡用來放置可能會用到的工具類，就像 Page 是用來處理分頁的

最後為了測試代碼的正確性，還撰寫了單元測試來看看代碼是否有誤，這邊使用了 H2 資料庫，好處是即使斷線了也能獲取資料庫數據，不需要去依賴外部的資料庫

MySQL 資料庫創建語法請參照 springboot-mall/src/main/resources/static/sql/data.sql 的內容

### 前端相關：

前端頁面使用了 Bootstrap 5 和 Thymeleaf 框架來撰寫代碼，前端想和後端溝通的話主要是使用 AJAX 技術來達成，少部份會用到 Thymeleaf 語法來獲取後端的數據

其中 AJAX 技術會和 Jquery 一起搭配使用，Jquery 可以獲取特定 id、name、class 的 value 值並對其進行操作

最後為了實作購物車功能，這裡還用到了 Cookie 的概念，藉由 Cookie 和 AJAX 技術來呈現購物車內的內容

### 使用說明：

https://torpid-satin-499.notion.site/4974659212ef4fff9b10edaba172a593

### AWS雲端部署網頁：

http://springbootmall-env.eba-gam37x3s.ap-northeast-1.elasticbeanstalk.com/
