<select name="avatar_list" size="10" onchange="document.images.img.src=document.profile.avatar_list.options[document.profile.avatar_list.selectedIndex].value;document.profile.link_avatar.value=document.profile.avatar_list.options[document.profile.avatar_list.selectedIndex].value;">

<% for(int i=0;i<55;i++){ %>

<option value="./avatars/avatar_<%= i %>.jpg">Avatar <%= i %></option>

<% } %>

</select>
