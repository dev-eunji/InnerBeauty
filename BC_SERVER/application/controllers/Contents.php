<?php
class Contents extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('ExhibitionModel');
		$this->load->model('PlayModel');
		$this->load->model('ContentsModel');
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
	public function getFavoriteContentsList($userId, $contentsType){
		$favoriteContentsList = $this->ContentsModel->getFavoriteContentsList($userId, $contentsType);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($favoriteContentsList);
	}
	public function registerFavoriteContents(){
		$favoriteValue['user_id'] = $this->input->post('user_id');
   		$favoriteValue['contents_id'] = $this->input->post('contents_id');
   		$favoriteValue['contents_type'] = $this->input->post('contents_type');

		$result = $this->ContentsModel->registerFavoriteContents($favoriteValue);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($result);
	}
	public function deleteFavoriteContents(){
		$deleteValue['user_id'] = $this->input->post('user_id');
   		$deleteValue['contents_id'] = $this->input->post('contents_id');
   		$deleteValue['contents_type'] = $this->input->post('contents_type');

		$this->ContentsModel->deleteFavoriteContents($deleteValue);
	}
}
?>
