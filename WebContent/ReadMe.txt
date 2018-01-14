Popup note:

	In the head section, after the inclusion of the header
		<script>
			$(document).ready(function() {
				$("#note").hide();
				$('#noteImage').click(function() {
				 	$("#note").slideToggle();     
				});
			});
		</script>
	
	The Note
		<img border="0" id="noteImage" alt="edit" src="images/base/question.png" width="20" height="20">		
		<div id="note">
		some note text
		</div>	
		
		
		
Force space
<span style="display:inline-block; width:2em;"></span>	

Radio hide / show
	In the head section , after the imports
	Change the input[name$='itemtype'] to the name of the radio options
	change the #itemtype to the id of the div sections
	<script>
		$(document).ready(function() {
		    $("input[name$='itemtype']").click(function() {
		        var test = $(this).val();
	
		        $("div.desc").hide();
		        $("#itemtype" + test).show();
		    });		    
		});
	</script>
	
	In the body
	Change the names to match the script above
	When adding more options, increase the value's
	Change it id's of the divs to match above
		<div id="myRadioGroup">
			<h2>Select the type to add</h2>
		    Non Audio Item<input type="radio" name="itemtype" checked="checked" value="1"  />
		    <br>
		    Audio Item<input type="radio" name="itemtype" value="2" />
		    <br>
		    <h2>Details:</h2>					
		    <div id="itemtype1" class="desc">
				First item stuff
		    </div>
		    <div id="itemtype2" class="desc" style="display: none;">
		        Second item stuff
		    </div>
		</div>

Ajax
Auto Refresh

	In the head section 
	The 1000 is the millisecond delay
	The url is the page to run
	variables can be passed in the data section that will be send via get / post if specified
	the result will be returned in html so it cannot return tag files
		<script>
			$(document).ready(function() {	
				setInterval(function() {
					$.ajax({
						url : 'Controller.do?nav=views.items.navigator.ItemListDisplay',
						dataType: "html",
						data : {					
							showImage : true,
							showLabel : true,
							filterType : '${filterType}',
						},
						success : function(responseText) {					
							$('#ajaxItem').html(responseText);
						}
					});
				}, 1000);
			});
		</script>
					
					
	In the body
	This div section will be replaced with the content of the url specified in the above section
		<div id="ajaxItem">
		</div>
		
Ajax - Control refresh
	In the head
	In the head bind to the change function of the first dropdown
	In the success code rebuild the dropdown
		<script>
			$(document).ready(function() {	
				$("#idtargetitem").change(function(){
					 $.ajax({
				        url: 'Controller.do?nav=ajax.navigator.ActionDropdown',
				        dataType: "html",
				        data : {
					        actionValue : $(this).val(),
				        },
				        success: function(responseText){
				        	$('#ajaxActionDropdown').html(responseText);
				        }
					});
				});		 					
			});
		</script>
		
	In the body
	The first dropdown should be the same id as the one in the ajax
	The second dropdown is will be repopulated on the section of the first
	For add pages, the choose sections in the dropdowns can be reduced by removing the default selected value
		<p>Item</p>
		<p>
			<select id="idtargetitem" name="idtargetitem" >
				<c:forEach items="${targetItemDropdownList}" var="targetItemDropdownListItem">
					<c:choose>
						<c:when test="${inputTargetItemDTO.idTargetItem != null && targetItemDropdownListItem.name == inputTargetItemDTO.idTargetItem}">
							<option value="${targetItemDropdownListItem.name}" selected="${targetItemDropdownListItem.name}">${targetItemDropdownListItem.text} </option>	
						</c:when>
						<c:otherwise>
							<option value="${targetItemDropdownListItem.name}">${targetItemDropdownListItem.text}</option>
						</c:otherwise>										
					</c:choose>									
				</c:forEach>
			</select>
		</p>
		
		<div id="ajaxActionDropdown">
			<p> Item Action :</p>
			<p>
				<select name="action" >
					<c:forEach items="${itemActionDropdownList}" var="itemActionDropdownListItem">
						<c:choose>
							<c:when test="${inputTargetItemDTO.action != null && itemActionDropdownListItem.name == inputTargetItemDTO.action}">
								<option value="${itemActionDropdownListItem.name}" selected="${itemActionDropdownListItem.name}" >${itemActionDropdownListItem.text}</option>
							</c:when>
							<c:otherwise>
								<option value="${itemActionDropdownListItem.name}">${itemActionDropdownListItem.text}</option>
							</c:otherwise>
						</c:choose>												
					</c:forEach>
				</select>
			</p>
		</div>