<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/views/layouts/user/breadcrumb.jsp" %>

<div class="book-detail page">
    <div class="container">
        <div class="primary-block clearfix">
            <div class="row">
                <div class="col-sm-4">
                    <div class="book-cover book detail-book-cover">

                        <img
                                src="<c:url value="/file-upload/images/${ bookInfo.img }"></c:url>"
                                class="img-responsive" alt="${ bookInfo.name }">
                        <div class="fade"></div>
                    </div>
                    <!-- /.book-cover -->
                </div>
                <!-- /.col -->

                <div class="col-sm-8">
                    <div class="book-detail-header">
                        <h2 class="book-title">${ bookInfo.name }</h2>
                        <div class="star-rating">
                            <c:forEach var="i" begin="1" end="${ bookInfo.star }">
                                <i class="fa fa-star color"></i>
                            </c:forEach>
                            <c:forEach var="i" begin="1" end="${ 5 - bookInfo.star }">
                                <i class="fa fa-star"></i>
                            </c:forEach>
                        </div>
                        <!-- /.star-rating -->
                    </div>
                    <!-- /.book-detail-header -->

                    <div class="book-detail-body">

                        <div class="detail-cart-button row clearfix">
                            <div class="pull-left col-md-6 col-sm-5 col-xs-12">
                                <div class="detail-book-price">
                                    <c:if test="${ bookInfo.pdf == null }">
                                        <a href="#pdf-not-found" class="price"
                                           onclick="showPDFNotFound();">Xem trực tuyến</a>
                                    </c:if>
                                    <c:if test="${ bookInfo.pdf != null }">
                                        <a
                                                href="<c:url value="/xem-sach-online/${ bookInfo.id }"></c:url>"
                                                target="_blank" class="price">Xem trực tuyến</a>
                                    </c:if>
                                </div>
                                <!-- /.detail-book-price -->
                            </div>
                            <!-- /.pull-left -->

                            <div class="pull-right col-md-6 col-sm-7 col-xs-12">
                                <div class="row product-actions">
                                    <c:if test="${ bookInfo.pdf == null }">
                                        <a href="#pdf-not-found"
                                           class="col-sm-6 btn btn-primary btn-addToCart"
                                           onclick="showPDFNotFound();"><i
                                                class="icon-plus fa fa-plus-circle"></i>Tải xuống</a>
                                    </c:if>
                                    <c:if test="${ bookInfo.pdf != null }">
                                        <a
                                                href="<c:url value="/file-upload/pdfs/${ bookInfo.pdf }"></c:url>"
                                                class="col-sm-6 btn btn-primary btn-addToCart"><i
                                                class="icon-plus fa fa-plus-circle"></i>Tải xuống</a>
                                    </c:if>
                                    <button title="Thích"
                                            class="hidden-xs btn btn-secondary btn-add-to-wishlist col-xs-2 "
                                            type="button" onclick="upvote();">
                                        <i class="far fa-thumbs-up"></i>
                                    </button>
                                    <button title="Không thích"
                                            class="hidden-xs btn btn-secondary btn-add-to-wishlist col-xs-2 "
                                            type="button" onclick="downvote();">
                                        <i class="far fa-thumbs-down"></i>
                                    </button>
                                </div>
                                <!-- /.row -->
                            </div>
                            <!-- /.pull-right -->
                        </div>
                        <!-- /.detail-cart-button -->

                        <div class="clearfix"></div>

                        <div class="product-description">
                            <h3>Tác giả và các vai trò khác</h3>
                            <p class="book-author">Tác giả:</p>
                            <c:forEach var="item" items="${ bookInfo.bookFeatureds }">
                                <c:if test="${item.author != null}">

                                        <span class="book-author-name">${ item.author.name }, </span>

                                </c:if>
                            </c:forEach>
                            <h3>Thể loại</h3>
                            <p class="book-author">Tác giả:</p>
                            <c:forEach var="item" items="${ bookInfo.bookFeatureds }">
                                <c:if test="${item.category != null}">

                                        <span class="book-category-name">${ item.category.name }, </span>

                                </c:if>
                            </c:forEach>
                            <h3>Nhà xuất bản</h3>
                            <p class="text-justify">Nhà xuất bản: ${ bookInfo.publishingHouse.name }</p>
                        </div>
                        <!-- /.product-description -->

                    </div>
                    <!-- /.book-detail-body -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-sm-12 product-description">
                    <h3>Mô tả</h3>
                    <p class="text-justify">${ bookInfo.description }</p>
                </div>
            </div>

            <div class="tab-holder clearfix">
                <div class="book-additional-details"></div>
                <!-- /.book-additional-details -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.primary-block -->
    </div>
    <!-- /.container -->
    <!-- ========================================= RELATE BOOKS MODULE ========================================= -->

    <div class="container">
        <div class="wow fadeInUp">
            <div class="module block-new-books module-block">
                <div class="module-heading">
                    <h2 class="module-title">Sách, tài liệu liên quan</h2>
                    <div class="customNavigation">
                        <a href="#" data-target="#new-carousel"
                           class="btn btn-navigation left-nav-arrow owl-prev"><i
                                class='icon fa fa-caret-left'></i></a> <a href="#"
                                                                          data-target="#new-carousel"
                                                                          class="btn btn-navigation right-nav-arrow owl-next"><i
                            class='icon fa fa-caret-right'></i></a>
                    </div>
                    <!-- /.customNavigation -->
                    <p class="module-subtitle">Những sách có liên quan dựa trên yếu
                        tố xác định là thể loại</p>
                </div>
                <!-- /.module-heading -->

                <div class="module-body">
                    <div class="books">

                        <div id="new-carousel" class="owl-carousel home-owl-carousel">
                            <c:forEach var="item" items="${ booksByCategory }">
                                <div class="item item-carousel">
                                    <div class="books">

                                        <div class="book">
                                            <div class="sale-ribbon">
                                                <div class="sale-ribbon-content">Liên quan</div>
                                            </div>
                                            <div class="book-cover">
                                                <div class="book-inner">

                                                    <img
                                                            src="<c:url value="/file-upload/images/${ item.img }"></c:url>"
                                                            width="193" height="261" alt="${ item.name }">
                                                    <div class="fade"></div>
                                                    <div class="book-price">

														<span class="price">
                                                             <c:url var="get_url"
                                                                    value="/chi-tiet-sach/${ item.id }"> </c:url>
                                                            <form:form id="bookDetails" method="GET"
                                                                       action="${ get_url }">
                                                                <input type="hidden" name="categoryId" value="${categoryId}"/>
                                                                <button type="submit" class="btn btn-link text-uppercase font-weight-bold" style="cursor:pointer;">
                                                                    Xem ngay
                                                                </button>
                                                            </form:form>
														</span>
                                                    </div>
                                                    <!-- /.book-price -->
                                                </div>
                                                <!-- /.book-inner -->
                                            </div>
                                            <!-- /.book-cover -->


                                            <div class="book-details">
                                                <h3 class="book-title" style="font-size: 16px;">
                                                    <c:url var="get_url"
                                                           value="/chi-tiet-sach/${ item.id }"> </c:url>
                                                        <form:form id="bookDetail" method="GET"
                                                                   action="${ get_url }">
                                                            <input type="hidden" name="categoryId" value="${categoryId}"/>
                                                            <button type="submit" class="btn btn-link text-uppercase font-weight-bold" style="cursor:pointer;">${ item.name }</button>
                                                        </form:form>
                                                </h3>
                                                <div class="star-rating">
                                                    <c:forEach var="i" begin="1" end="${ item.star }">
                                                        <i class="fa fa-star color"></i>
                                                    </c:forEach>
                                                    <c:forEach var="i" begin="1" end="${ 5 - item.star }">
                                                        <i class="fa fa-star"></i>
                                                    </c:forEach>
                                                </div>
                                                <!-- /.star-rating -->

                                            </div>
                                            <!-- /.book-details -->
                                        </div>
                                        <!-- /.book -->

                                    </div>
                                    <!-- /.books -->
                                </div>
                                <!-- /.item -->
                            </c:forEach>
                        </div>
                        <!-- /#new-carousel -->
                    </div>
                    <!-- /.books -->
                </div>
                <!-- /.module-body -->
            </div>
            <!-- /.module -->
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->
    <!-- ========================================= RELATE BOOKS MODULE : END ========================================= -->
</div>
<!-- /.book-detail page -->

<script type="text/javascript">
    function upvote() {
        $(document).ready(function (e) {
            $.post("<c:url value="/thich-sach"></c:url>", {
                id: ${ bookInfo.id }
            }, function (data) {
                if (data == "Success")
                    alert("Bạn đã đánh giá thích sách thành công!");
                else if (data == "Failed")
                    alert("Bạn đã đánh giá thích sách không thành công, hãy thử lại nhé!");
                else
                    alert("Dữ liệu đầu vào không hợp lệ!");
            });
        });

    }
    function downvote() {
        $(document).ready(function (e) {
            $.post("<c:url value="/khong-thich-sach"></c:url>", {
                id: ${ bookInfo.id }
            }, function (data) {
                if (data == "Success")
                    alert("Bạn đã đánh giá không thích sách thành công!");
                else if (data == "Failed")
                    alert("Bạn đã đánh giá không thích sách không thành công, hãy thử lại nhé!");
                else
                    alert("Dữ liệu đầu vào không hợp lệ!");
            });
        });
    }

</script>

<c:if test="${ bookInfo.pdf == null }">
    <script type="text/javascript">
        function showPDFNotFound() {
            alert("PDF của sách này chưa sẵn có!");
        }
    </script>
</c:if>
