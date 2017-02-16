<?php
class Play extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('PlayModel');
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

	public function addClickNumToPlay(){
		$playId = $this->input->post('play_id');
		$result = $this->PlayModel->addClickNumToExhibition($playId);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($result);
	}

	public function getGlobalFavoritePlay(){
		$playData = $this->PlayModel->getGlobalFavoritePlay();
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
   		echo json_encode($playData);  
	}
}
?>
