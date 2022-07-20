<%@include file="/WEB-INF/views/common/taglib.jsp"%>


<%--
  Created by IntelliJ IDEA.
  User: asids
  Date: 5/3/2022
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trang chủ </title>
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/fontawesome-free/css/all.min.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bootstrap 4 -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
    <link rel="stylesheet"
          href="<c:url value="/asset/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css"/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
    <!-- JQVMap -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/jqvmap/jqvmap.min.css"/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/dist/css/adminlte.min.css"/>">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/overlayScrollbars/css/OverlayScrollbars.min.css"/>">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/daterangepicker/daterangepicker.css"/>">
    <!-- summernote -->
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/summernote/summernote-bs4.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/asset/admin/plugins/datatables-bs4/css/dataTables.bootstrap4.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/views/admin/navbar.jsp" %>
<!-- /.navbar -->

<!-- Main Sidebar Container -->
<%@include file="/WEB-INF/views/admin/sidebar.jsp" %>

<!-- Content Wrapper. Contain;s page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Dashboard</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard v1</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <!-- Small boxes (Stat box) -->
            <decorator:body/>

        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@include file="/WEB-INF/views/admin/footer.jsp" %>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
</aside>

<!-- jQuery -->
<script src="<c:url value="/asset/admin/plugins/jquery/jquery.min.js"/>"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<c:url value="/asset/admin/plugins/jquery-ui/jquery-ui.min.js"/>"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="<c:url value="/asset/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- ChartJS -->
<script src="<c:url value="/asset/admin/plugins/chart.js/Chart.min.js"/>"></script>
<!-- Sparkline -->
<script src="<c:url value="/asset/admin/plugins/sparklines/sparkline.js"/>"></script>
<!-- JQVMap -->
<script src="<c:url value="/asset/admin/plugins/jqvmap/jquery.vmap.min.js"/>"></script>
<script src="<c:url value="/asset/admin/plugins/jqvmap/maps/jquery.vmap.usa.js"/>"></script>
<!-- jQuery Knob Chart -->
<script src="<c:url value="/asset/admin/plugins/jquery-knob/jquery.knob.min.js"/>"></script>
<!-- daterangepicker -->
<script src="<c:url value="/asset/admin/plugins/moment/moment.min.js"/>"></script>
<script src="<c:url value="/asset/admin/plugins/daterangepicker/daterangepicker.js"/>"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<c:url value="/asset/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"/>"></script>
<!-- Summernote -->
<script src="<c:url value="/asset/admin/plugins/summernote/summernote-bs4.min.js"/>"></script>
<!-- overlayScrollbars -->
<script src="<c:url value="/asset/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/asset/admin/dist/js/adminlte.js"/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/asset/admin/dist/js/demo.js"/>"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value="/asset/admin/dist/js/pages/dashboard.js"/>"></script>
<script src="<c:url value="/asset/admin/plugins/datatables/jquery.dataTables.min.js"  />"></script>
<script src="<c:url value="/asset/admin/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"  />"></script>
</body>
</html>
