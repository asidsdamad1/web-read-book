<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Catalog</title>
</head>
<body>
<button id="addTag" class="btn btn-primary"><i class="fa fa-plus"></i> Thêm Danh Mục</button>
<table class="table table-striped"  id="lstPost2" >
	<thead>
	<tr>
		<th scope="col">CatalogID</th>
		<th scope="col">Catalog Name</th>
		<th scope="col">Priority</th>
		<th scope="col">Action</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${listCat }" var="cat">
		<tr>
			<td>${cat.catalogId }</td>
			<td>${cat.catalogName }</td>
			<td>${cat.priority }</td>
			<td><a href="get?id=${cat.catalogId }">Update</a></td>
		</tr>
	</c:forEach>

	</tbody>
</table>

	<a href="new">Create New Catalog</a>

<!--Add tag -->
<div class="modal fade" id="addModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Thêm Danh Mục</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<input type="text" required id="CatalogName" placeholder="Nhập tên danh mục..." class="form-control pb-1"/>
				<div style="margin-top:10px;"></div>
				<hr>
				<input type="number" required id="newPri" placeholder="Nhập priority" class="form-control"/>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="ConfirmAdd" class="btn btn-success">Thêm</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>

		</div>
	</div>
</div>
<script type="text/javascript">

	var newModal = $('#addModal');
	var delmodal = $('#deleteModal');
	var editModal = $('#editModal');

	$('#addTag').click(function () {
		$('#TagName').val(null);
		$('#addModal').modal('show');
	});
</script>
</body>

</html>