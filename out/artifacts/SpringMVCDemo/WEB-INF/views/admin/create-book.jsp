<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Trang quản trị/Tạo mới sách</h1>
	<a href="#"
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
					<div><a class="text-white-50 small" href="<c:url value="/quan-tri/tao-moi-sach"></c:url>">Tắt thông báo này</a></div>
				</div>
			</div>
		</c:if>
	</div>
</div>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Tạo mới sách</h6>
	</div>
	<div class="card-body">
		<c:url var="post_url" value="/quan-tri/tao-moi-sach"></c:url>
		<form:form method="POST" action="${ post_url }"
			modelAttribute="book">
			<div class="form-horizontal">

				<div class="form-group">
					<label class="control-label" for="name">Tên sách</label>
					<div>
						<form:input path="name"
							cssClass="form-control text-box single-line" />
							<form:errors path="name" cssClass="field-validation-valid text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="description">Mô tả sách</label>
					<div>
						<form:textarea path="description" cssClass="form-control" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label w-100">
						Chọn thể loại
						<input class="form-control text-box single-line w-100"  form="categoryIds" id="my-input" data-dropdown="true" data-tags="true" />
					</label>
					<div>
						<form:select id="multiple-select" path="categoryIds" cssClass="form-control" multiple="true">
							<form:options items="${ categories }" itemValue="id" itemLabel="name"/>
						</form:select>
						<form:errors path="category.id" cssClass="field-validation-valid text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label w-100">
						Chọn tác giả
						<input class="form-control text-box single-line w-100"  form="authorIds" id="author-input" data-dropdown="true" data-tags="true" />
					</label>
					<div>
						<form:select id="multiple-select-author" path="authorIds" cssClass="form-control" multiple="true">
							<form:options items="${ authors }" itemValue="id" itemLabel="name"/>
						</form:select>
						<form:errors path="author.id" cssClass="field-validation-valid text-danger"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label" for="publishingHouse.id">Chọn nhà xuất bản</label>
					<div>
						<form:select onchange="val()" id="select_id" path="publishingHouse.id" cssClass="form-control">
							<form:options items="${ publishingHouses }" itemValue="id" itemLabel="name"></form:options>
						</form:select>
						<form:errors path="publishingHouse.id" cssClass="field-validation-valid text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<input type="submit" value="Tạo mới" class="btn btn-success">
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="card-footer">
		<a class="btn btn-primary"
			href="<c:url value="/quan-tri/danh-sach-sach"></c:url>">Quay về danh
			sách</a>
	</div>
</div>
<script>
	mobiscroll.select('#multiple-select', {
		inputElement: document.getElementById('my-input'),
		touchUi: false,
		onChange: function(ev, inst) {
			console.log(ev.value); // the selected user object
		},
	});

	mobiscroll.select('#multiple-select-author', {
		inputElement: document.getElementById('author-input'),
		touchUi: false,
		onChange: function(ev, inst) {
			console.log(ev.value); // the selected user object
		},
	});

</script>