<?php
ob_start();
system('python body.py');
ob_clean();

echo "{'respond':'Python script executed!'}";

?>