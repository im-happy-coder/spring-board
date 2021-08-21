<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Spring Project</title>

    <!-- Bootstrap core CSS -->
    <!-- <link href="/resources/assets/css/bootstrap.css" rel="stylesheet"> -->
   <%--  <link href="<c:url value="/resources/assets/css/bootstrap.css" />"  rel="stylesheet">  --%>
    <link href="<%=request.getContextPath()%>/resources/assets/css/bootstrap.css"  rel="stylesheet">
    <!--external css-->
    <link href="<%=request.getContextPath()%>/resources/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/assets/css/zabuto_calendar.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/assets/lineicons/style.css"/>    
    
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resources/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/assets/css/style-responsive.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/resources/assets/js/chart-master/Chart.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>Spring Project</b></a>
            <!--logo end-->
            
                <!--  notification end -->
            
          
        </header>
      <!--header end-->