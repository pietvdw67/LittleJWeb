<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<TABLE>
		<c:forEach items="${itemDTOlist}" var="itemDTOlistItem">
			<tr>
				<td>
					<a href="Controller.do?nav=processor.statuschange.navigator.StatusChangeUpdate&iditem=${itemDTOlistItem.idItem}&status=${itemDTOlistItem.status}">
						<c:if test="${showImage eq true}">
							<c:choose>
								<c:when test="${itemDTOlistItem.status eq '1'}">
									<image src="${itemDTOlistItem.itemTypeDTO.imagePathOn}" alt="${itemDTOlistItem.description}" width="40" height="40" />
								</c:when>
								<c:otherwise>
									<image src="${itemDTOlistItem.itemTypeDTO.imagePathOff}" alt="${itemDTOlistItem.description}" width="40" height="40" />
								</c:otherwise>
							</c:choose>	
						</c:if>
						<c:if test="${showLabel eq true}">
							${itemDTOlistItem.description}
						</c:if>
					</a>
				</td>
				<td>
					<a href="Controller.do?nav=setup.hardware.items.navigator.EditItem&idItem=${itemDTOlistItem.idItem}">
						<img border="0" alt="edit" src="images/base/setup.png" width="40" height="40">
					</a>
					
					<a href="Controller.do?nav=views.sceneItem.navigator.AddCustomSceneItem&type=item&id=${itemDTOlistItem.idItem}">
						<img border="0" alt="edit" src="images/base/sceneadd.png" width="40" height="40">
					</a>
					
					<a href="Controller.do?nav=views.scheduleItem.navigator.AddCustomScheduleItem&type=item&id=${itemDTOlistItem.idItem}">
						<img border="0" alt="Add schedule" src="images/base/scheduleadd.png" width="40" height="40">
					</a>
					
					<c:choose>
						<c:when test="${filterType eq 'favourite' }">
							<a href="Controller.do?nav=views.favourites.navigator.RemoveCustomFavourite&type=item&id=${itemDTOlistItem.idItem}">
								<img border="0" alt="edit" src="images/base/favouritesremove.png" width="40" height="40">
							</a>
						</c:when>
						<c:otherwise>										
							<a href="Controller.do?nav=views.favourites.navigator.AddCustomFavourite&type=item&id=${itemDTOlistItem.idItem}">
								<img border="0" alt="edit" src="images/base/favouritesadd.png" width="40" height="40">
							</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>	
	</TABLE>
