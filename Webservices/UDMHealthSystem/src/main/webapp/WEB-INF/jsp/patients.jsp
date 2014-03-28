<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<html>
	<head>
		<title>Patients</title>
		<link type="text/css" href="<c:url value="/resources/css/displayTagStyle.css"/>" rel="stylesheet" />	
		<script type="text/javascript" src="<c:url value="/resources/js/picnet.table.filter.min.js" />"></script>
<%-- 		<script type="text/javascript" src="<c:url value="/resources/js/jquery.zclip.min.js" />"></script> --%>
		<script type="text/javascript">
		var contextPath = "${pageContext.servletContext.contextPath}";
		
		$(function() {
			$(document).ready(function() {
				var options = {
						clearFiltersControls: [$('#cleanFilters')]
				};
			
				$('.listtable').tableFilter(options);
				$('table.listTable thead tr.filters td input#_filter_6.filter').hide();
				$('#filterButton').button();
				$('#clearButton').button();
				$('#cleanFilters').button({
					text: false,
					icons: {
		                primary: "ui-icon-circle-close"
		            }
				});
				
				
				$( "#accordion" ).accordion({
					autoHeight: false,
					navigation: true
				});
				$('#accordion').accordion( "option", "icons", false );
				$('#accordion').accordion( "option", "active", 1);
				
				// Send the filters to the controller
				$('#filterButton').click(function(event) {
					var serviceVal = $('#serviceListCombobox').val();
					var statusVal  = $('#statusListCombobox').val();
					var fromDate = $.datepicker.formatDate('dd-M-y', $( "#from" ).datepicker( 'getDate' ));
					var toDate = $.datepicker.formatDate('dd-M-y', $( "#to" ).datepicker( 'getDate' ));
					var parameters = '?service='+serviceVal+'&status='+statusVal+'&fromDate='+fromDate+'&toDate='+toDate;
					window.location.href = contextPath + '/admin/logs' + parameters;
				});
				
				//Reset filters.
				$('#clearButton').click(function(event){
					window.location.href = contextPath + '/admin/logs';
				});
								
				if($('.pagelinks').children().length > 0){
					$('.pagebanner').appendTo('#toolbar');
					$('.pagelinks').appendTo('#toolbar');
					$('table.listTable thead tr').addClass('ui-widget-header ui-state-active');
					$('#toobar').show();
				}else{
					$('#toolbar').hide();
				}
				
	     });
				
		});
			
		function displayDetails(id) {
			$.get("${pageContext.servletContext.contextPath}/admin/logs/" + type +"Xml/" + id,
					function(data) {
						var escapedData = data.replace(/</g, '&lt;').replace(/>/g, '&gt;');
						$('#dialog').empty();
						$('#dialog').append('<pre>' + escapedData + '</pre>');					
						$('#dialog').dialog('open');					
			});
		}

		</script>
	</head>
	<body>
		<h1  class="ui-widget-header"  style="text-align:center;">Patients</h1>
		<div id="accordion">
			<h3>Filters</h3>
			<div>
				<div id="filterContainer" class="ui-widget-content ui-corner-all">
    				<h4 class="ui-widget-header ui-corner-all">Filters</h4>
					<div class="divRow">
						<table>	
							<tr>
								<td><label>First Name: </label></td>
								<td><input type="text" name="firstName" id="firstName" /></td>
								<td><label>Last Name: </label></td>
								<td><input type="text" name="lastName" id="lastName" /></td>
							</tr>
							<tr>
								<td><label>Email: </label></td>
								<td><input type="text" name="email" id="email" /></td>
								<td><label>State: </label></td>
								<td><input type="text" name="state" id="state" /></td>
							</tr>
						</table>
					</div>
					<div class="divRow">
						<button id="filterButton">Filter</button>
						<button id="clearButton">Clear Filter</button>
					</div>
				</div>				
			</div>
			<h3>Results</h3>
			<div>
			    <div id="toolbar" class="ui-widget-header ui-corner-all">
					<button id="cleanFilters"></button>
				</div>
				<display:table class="listTable" name="${userAccessList}" sort="external" pagesize="50" partialList="true" size="resultSize" requestURI="${pageContext.servletContext.contextPath}/admin/patients" decorator="com.udm.health.displaytag.PatientDecorator">
				<display:column property="firstName" title="First Name" sortable="true" />
				<display:column property="lastName" title="Last Name" sortable="true" />
				<display:column property="dateOfBirth" title="Date Of Birth" sortable="true" />
				<display:column property="street" title="Street" sortable="true"/>
				<display:column property="city" title="City" sortable="true"/>
				<display:column property="state" title="State" sortable="true"/>
				<display:column property="viewDetails" title="View Details"/>
				</display:table>
			</div>
		</div>
		
		<div id="dialog"></div>
	</body>
</html>