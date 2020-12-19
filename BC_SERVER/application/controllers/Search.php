<?php
class Search extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('SearchModel');
	}

	public function searchContentsList(){
		$searchWord = $this->input->post('search_word');
		$searchData = $this->SearchModel->searchContentsList($searchWord);
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($searchData); 
	}

}
?>
