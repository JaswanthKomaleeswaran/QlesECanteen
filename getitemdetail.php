<?php
$cate=$_GET['category'];

$cn= mysqli_connect("localhost","sdfwersd_test","test1234","sdfwersd_canteen");
if (!$cn)
  {
  die('Could not connect: ' . mysqli_error($cn));
  }

$sql="select * from products where category like '$cate%';";

$r = mysqli_query($cn,$sql);

        $result = array();
        $co=1;

        while($res = mysqli_fetch_row($r))
        { 
            $result[] = $res;

        }

        echo json_encode($result);
        mysqli_close($con); 

?>
