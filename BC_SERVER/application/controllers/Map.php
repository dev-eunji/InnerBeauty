<?php
class Map extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('MapModel');
	}
	
	public function getLatLngFromAddress(){
		$address = $this->input->post('address');
		$result = $this->MapModel->getLatLngFromAddress($address);
		echo $result; 
	}
}
?>
