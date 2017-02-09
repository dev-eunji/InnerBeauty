<?php
class Contents extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('ExhibitionModel');
		$this->load->model('PlayModel');
	}

	public function getExhibition($exhibitionId){
		$exhibitionData = $this->ExhibitionModel->getExhibitionInfo($exhibitionId);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($exhibitionData); 
	}
	public function getExhibitionList($page){
		$exhibitionData = $this->ExhibitionModel->getExhibitionList($page);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
   		echo json_encode($exhibitionData);  
	}
	public function getPlay($playId){
		$playData = $this->PlayModel->getPlayInfo($playId);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($playData); 
	}
	public function getPlayList($page){
		$playData = $this->PlayModel->getPlayList($page);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
   		echo json_encode($playData);  
	}


	public function test($a){
		$exhibitionData = $this->ExhibitionModel->test($a);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
   		echo json_encode($exhibitionData);  
	}
}
?>
