<?php
class Contents extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('ContentsModel');
	}
	
	public function getFavoriteContentsList($userId){
		$favoriteContentsList = $this->ContentsModel->getFavoriteContentsList($userId);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($favoriteContentsList);
	}

	public function getFavoriteContentsListByContentsType($userId, $contentsType){
		$favoriteContentsList = $this->ContentsModel->getFavoriteContentsListByContentsType($userId, $contentsType);
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
		$result = $this->ContentsModel->deleteFavoriteContents($deleteValue);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($result);
	}
}
?>
