<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<html>
	<head>
		<title>BlsWs Web Admin -- Request Logs</title>
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
				$('table.listTable thead tr.filters td input#_filter_3.filter').hide();
				$('table.listTable thead tr.filters td input#_filter_4.filter').hide();
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
				
				
				//Date Filter
				$( "#from" ).datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 1,
					onSelect: function( selectedDate ) {
						$( "#to" ).datepicker( "option", "minDate", selectedDate );
					}
				});
				$( "#to" ).datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 1,
					onSelect: function( selectedDate ) {
						$( "#from" ).datepicker( "option", "maxDate", selectedDate );
					}
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
				
				
			$(function() {
				$('#dialog').dialog({ autoOpen: false, 
					                 width: 800,
					                 modal: true,
					                 show: {
					                     effect: "blind",
					                     duration: 1000
					                   },
					                   hide: {
					                     effect: "explode",
					                     duration: 1000
					                   },open: function(event, ui){
					                       $('<a />', {
					                           'class': 'linkClass',
					                           text: 'Copy Text',
					                           href: '#',
					                           id: 'copy-xml'
					                       })
					                       .appendTo($(".ui-dialog-buttonpane"))
					                       .zclip({
                          				        path: contextPath +'/resources/js/ZeroClipboard.swf',
                           				        copy:$('#dialog').text()
                         				    });
					               },close: function() { 
					            	   $(".ui-dialog-buttonpane a.linkClass").remove();
					                },					                   
					                 buttons: {
							             Cancel: function(){
							            	 $('#dialog').dialog("close");
								             }
						                 } 
                });
				    
			});
		
			function displayXml(type, id) {
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
		<h1  class="ui-widget-header"  style="text-align:center;">Request Logs</h1>
		<div id="accordion">
			<h3>Filters</h3>
			<div>
				<div id="filterContainer" class="ui-widget-content ui-corner-all">
    				<h4 class="ui-widget-header ui-corner-all">Filters</h4>
					<div class="divRow">
						<table>	
							<tr>
								<td><label>Service: </label></td>
								<td>
									<select id="serviceListCombobox">
										<option value="">Select one...</option>
										<c:forEach var="service" items="${serviceList}">
											<option value="${service.serviceName}">${service.description}</option>
										</c:forEach>
									</select>
								</td>
								<td><label>Status: </label></td>
								<td>
									<select id="statusListCombobox">
										<option value="">Select one...</option>
										<c:forEach var="status" items="${statusList}">
											<option value="${status.code}">${status}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label for="from">From:</label></td>
								<td><input type="text" id="from" name="from"/></td>
								<td><label for="to">to:</label></td>
								<td><input type="text" id="to" name="to"/></td>
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
				<display:table class="listTable" name="${logs}" sort="external" pagesize="50" partialList="true" size="resultSize" requestURI="${pageContext.servletContext.contextPath}/admin/logs" decorator="com.udm.health.displaytag.RequestLogDecorator">
				<display:column property="serviceName" title="Service" sortable="true" />
				<display:column property="receivedDate" title="Date" sortable="true" />
				<display:column property="responseCode" title="Status" sortable="true" />
				<display:column property="requestXml" title="Request" />
				<display:column property="responseXml" title="Response"/>
				</display:table>
			</div>
		</div>
		
		<div id="dialog"></div>
	</body>
</html>