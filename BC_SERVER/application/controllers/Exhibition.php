<?php
class Exhibition extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('ExhibitionModel');
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
	public function addClickNumToExhibition(){
		$exhibitionId = $this->input->post('exhibition_id');
		$result = $this->ExhibitionModel->addClickNumToExhibition($exhibitionId);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($result);
	}
	public function getGlobalFavoriteExhibition(){
		$exhibitionData = $this->ExhibitionModel->getGlobalFavoriteExhibition();
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
   		echo json_encode($exhibitionData);  
	}
}
?>
