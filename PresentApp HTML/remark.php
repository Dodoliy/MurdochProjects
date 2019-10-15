<?php
session_start();
include("Sqlconnect.php");
include("menubar.php");
?>

<!DOCTYPE html>
<script type="text/javascript">
</script>

<html>
<head>
<title>PresentApp</title>
<link href="remark.css" rel="stylesheet" type="text/css">
<link href="menubar.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
<h1>Comments</h1>
<?php
$scheduleNum = $_POST["hiddenSchedule"];
$getRemark = mysqli_query($conn, "SELECT assessmentid, speech, clarity, explanation
									, relevance, design, knowledge, remark, totalscore
									FROM assessmentmark WHERE scheduleID = '$scheduleNum'")
									or die ($conn->error);
									
	while($row = $getRemark->fetch_assoc())
	{
		echo "<div class='remark'>";
		echo "Assessment ID: ". $row["assessmentid"]. "</br>".
				"Speech: ". $row["speech"]. " || ".
				"Clarity: ". $row["clarity"]. " || ".
				"Explanation: ". $row["explanation"]. " || ".
				"Relevance: ". $row["relevance"]. " || ".
				"Design: ". $row["design"]. " || ".
				"Knowledge: ". $row["knowledge"]. " || ".
				"Total Score: ". $row["totalscore"]. "</br>";
		echo "<div class='comment'>";		
		echo "Remark:". "</br>". $row["remark"];
		echo "</div>";
		echo "</div>";
	}
?>

</div>
<!-- END BAR IN WEBSITE -->
<div class="endbar"></div>
<img src="sevenApp.png" alt="sevenAppLogo" width="125" height="125" style="margin-left:30px;">
</body>
</html>