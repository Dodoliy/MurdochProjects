<?php
session_start();
include("Sqlconnect.php");
include("menubar.php");
$phparray = array();
$x = 0; //array index data-box value
$sql = "SELECT image, name, course, studentid FROM student";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html>
<head>
<script>
function myFunction() {
  // Declare variables
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('myInput');
  filter = input.value.toUpperCase();
  ul = document.getElementById("studentList");
  li = ul.getElementsByTagName('li');

  // Loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}
</script>
<title>PresentApp</title>
<link href="searchResult.css" rel="stylesheet" type="text/css">
<link href="menubar.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">
<!-- SEARCH BAR-->
	<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
<!-- STUDENTS/DISPLAY STUDENTS USING WHILE LOOP -->	

<div class="students">
	<ul id="studentList">
	<form action="profile1.php" method="post" id="form1" name="form1">
	<input type="hidden" id="hiddenVal" name="hiddenVal" />
	<?php
	while ($row = $result->fetch_assoc())
	{
		?>
	<li><a><div class="studentInfo" data-box="<?php echo $x ?>" onclick="sdisplay(this)">
	<?php echo $row ["name"]. "<br>" .$row["studentid"]. "<br>". $row["course"];
		$phparray[] = $row["studentid"];
		$x+=1;
	?>
	</div></a></li>
	<?php
	}
	?>
	</form>
</ul>


</div>
</div>
<!-- END BAR IN WEBSITE -->
<div class="endbar"></div>
<img src="sevenApp.png" alt="sevenAppLogo" width="125" height="125" style="margin-left:30px;">

<script>
STUD_NUM = <?php echo json_encode($phparray); ?>;
</script>
<!-- RETURN BOXVALUE INDEX -->
<script type="text/javascript">
var boxvalue;
var modal;
var STUD_NUM

function sdisplay(boxdata)
{
	boxvalue = boxdata.getAttribute("data-box");
	var num = boxvalue;
	var studnum = STUD_NUM;
	var sliced = studnum[num]
	document.getElementById("hiddenVal").value = sliced;
	document.form1.submit();
}
</script>

</form>
</body>
</html>