<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Trang quản trị/Danh sách sách</h1>
    <a href="<c:url value="/quan-tri/tao-moi-sach"></c:url>"
       class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
        <i class="fas fa-download fa-sm text-white-50"></i> Tạo mới
    </a>
</div>

<div class="row">
    <div class="col-lg-12 mb-4">
        <c:if test="${ state != null }">
            <div class="card bg-secondary text-white shadow">
                <div class="card-body">
                        ${ state }
                    <div><a class="text-white-50 small" href="<c:url value="/quan-tri/danh-sach-sach"></c:url>">Tắt
                        thông báo này</a></div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Danh sách sách</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <div id="dataTable" class="dataTables_wrapper dt-bootstrap4">


                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-bordered dataTable" id="dataTable"
                               width="100%" cellspacing="0" role="grid"
                               aria-describedby="dataTable_info" style="width: 100%;">
                            <thead>
                            <tr role="row">
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1" aria-sort="ascending">ID sách
                                </th>
                                <th class="sorting" tabindex="0" aria-controls="dataTable"
                                    rowspan="1" colspan="1" aria-sort="ascending">Tên sách
                                </th>

                                <th>Công cụ</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th rowspan="1" colspan="1">ID sách</th>
                                <th rowspan="1" colspan="1">Tên sách</th>
                                <th rowspan="1" colspan="1">Công cụ</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="item" items="${ books }">
                                <tr role="row">
                                <td>${ item.id }</td>
                                <td>${ item.name }</td>

                                <td>
                                    <c:url var="post_url" value="/quan-tri/xoa-sach"></c:url>
                                    <form:form id="deleteBook${ item.id }" method="POST" action="${ post_url }">
                                        <input type="hidden" name="id" value="${ item.id }"/>
                                    </form:form>
                                    <a class="btn btn-success"
                                       href="<c:url value="/quan-tri/chinh-sua-sach/${ item.id }"></c:url>">
                                        Chỉnh sửa </a> |
                                    <a class="btn btn-success"
                                       href="<c:url value="/quan-tri/hinh-anh-cua-sach/${ item.id }"></c:url>">
                                        Hình ảnh </a> |
                                    <a class="btn btn-success"
                                       href="<c:url value="/quan-tri/pdf-cua-sach/${ item.id }"></c:url>">
                                        PDF </a> |
                                    <a class="btn btn-success"
                                       href="<c:url value="/quan-tri/tac-gia-cua-sach/${ item.id }"></c:url>">
                                        Tác giả </a> |
                                    <a class="btn btn-success"
                                       href="<c:url value="/quan-tri/the-loai-cua-sach/${ item.id }"></c:url>">
                                        Thể loại </a> |
                                    <a class="btn btn-primary"
                                       href="<c:url value="/quan-tri/chi-tiet-sach/${ item.id }"></c:url>">
                                        Xem chi tiết </a> |
                                    <button type="submit" class="btn btn-danger" form="deleteBook${ item.id }">
                                        Xóa
                                    </button>
                                </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row"></div>
            </div>
        </div>
    </div>
</div>