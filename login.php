<?php

$cn= mysqli_connect("localhost","sdfwersd_test","test1234","sdfwersd_canteen");
if (!$cn)
  {
  die('Could not connect: ' . mysqli_error($cn));
  }
$usercred=$_GET['name'];
$userdetails=explode(",",$usercred);


$sql="select * from users where userid like '" . $userdetails[0] . "'"; 

$rows=mysqli_query($cn,$sql);
$result=0;
if($row=mysqli_fetch_row($rows))
{
	if($row[1]==$userdetails[1])
	{
		$result=3;
	}
	else
	{
		$result=1;
	}
}
echo $result;
?>
