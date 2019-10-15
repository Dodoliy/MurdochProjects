<?php
session_start();
include("Sqlconnect.php");
include("menubar.php");
$phparray = array();
$x = 0; //array index
?>

<!DOCTYPE html>
<script type="text/javascript">
</script>

<html>
<head>
<title>PresentApp</title>
<link href="profile1.css" rel="stylesheet" type="text/css">
<link href="menubar.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
<div class="profile">
<!-- PROFILE DETAILS -->
<?php
$condition = $_POST["hiddenVal"];

$result = mysqli_query($conn,"SELECT image, name, course, studentid, email 
						FROM student WHERE studentid = '$condition'") or die ($conn->error);
?>
<?php 
while($row = $result->fetch_assoc())
{
	$image = $row ["image"];
	$name = $row ["name"];
	$studentID = $row["studentid"];
	$course = $row["course"];
	$email = $row["email"];
}
?>
<img class="profilePicture" src="<?php echo $image; ?>" onerror="this.src='default.png'" />
<?php
echo "<div class='profileDetails'>";
	echo	$name;
	echo	"</br>";
	echo	$studentID;
	echo	"</br>";
	echo	$course;
	echo	"</br>";
	echo 	$email;
	echo "</div>";
?>
<?php
$getscheduleID = mysqli_query($conn,"SELECT DISTINCT scheduleID FROM assessmentmark
				WHERE studentid = '$condition'");
				
?>
<!-- CHART -->
<div id="chart">
	<div>
	<table>
		<tbody>
			<tr class="charttitleholder">
				<td colspan="8" class="charttitle">
				Student Performance </br> (Average Marks out of 30)</td>
			</tr>
			<tr class="bars">
			<?php
			while($row = $getscheduleID->fetch_assoc())
			{
			?>		
				<td>
				<?php 
					$count = 0;
					$totalMarks = 0;
					$scheduleID = $row ["scheduleID"];
					$getAverageMarks = mysqli_query($conn, "SELECT totalscore FROM assessmentmark
															WHERE studentid = '$condition'
															AND scheduleID = '$scheduleID'")
															or die ($conn->error);
															
					while($graphScore = $getAverageMarks->fetch_assoc())
					{
						$marks = $graphScore["totalscore"];
						$totalMarks = $totalMarks + $marks;
						$count = $count+1;
					}
					$averageMarks = $totalMarks/$count;
					echo $averageMarks;
					$bar_height = $averageMarks *2.5;
					echo "<div style='height: $bar_height%; background-color: red;';
							data-box='$scheduleID' onclick='sdisplay(this)'>";
					echo "</div>";
					
					$getscheduleDetails = mysqli_query($conn, "SELECT date, module FROM schedule
																WHERE scheduleID = '$scheduleID'")
																or die ($conn->error);
					while($graphData = $getscheduleDetails->fetch_assoc())
					{
						echo $graphData["module"]. "</br>" .$graphData["date"];
					}
				?>
				</td>
			<?php	
			}
			?>			
			</tr>
		</tbody>	
	</table>
	</div>
</div>
<!-- END OF CHART -->
<!-- START OF ASSESSOR -->
<h1>Registered assessor for these presentations:</h1>
	<div class="assessor">
	<?php
	$getAssessor = mysqli_query($conn, "SELECT scheduleID FROM assessor
										WHERE studentid = '$condition'") 
										or die ($conn->error);
		while($row = $getAssessor->fetch_assoc())
		{
			$assessorScheduleID = $row["scheduleID"];
			$getAssessorDetails = mysqli_query($conn, "SELECT date, time, venue, module
														FROM schedule
														WHERE scheduleID = '$assessorScheduleID'")
														or die ($conn->error);
			while($get = $getAssessorDetails->fetch_assoc())
			{
				echo "<div class='assessorDetails'>";
				echo $get["date"]. "</br>". $get["time"].
						"</br>". $get["venue"]. "</br>".
						$get["module"];
				echo "</div>";
			}
			
		}
	?>
	</div>

</div>
</div>
<form action='remark.php' method='post' id='form2' name='form2'>
<input type='hidden' id='hiddenSchedule' name='hiddenSchedule'>
</form>
<script type="text/javascript">
var boxvalue;

function sdisplay(boxdata)
{
	boxvalue = boxdata.getAttribute("data-box")
	document.getElementById("hiddenSchedule").value = boxvalue;
	document.form2.submit();
}
</script>
<!-- END BAR IN WEBSITE -->
<div class="endbar"></div>
<img src="sevenApp.png" alt="sevenAppLogo" width="125" height="125" style="margin-left:30px;">
</body>
</html>