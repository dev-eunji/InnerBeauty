<?php
class Users extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model('UserModel');
	}

	public function addUserWithSNS(){
   		$userValue['user_id'] = $this->input->post('user_id');
   		$userValue['user_name'] = $this->input->post('user_name');
   		$userValue['user_email'] = $this->input->post('user_email');
   		$userValue['user_profile'] = $this->input->post('user_profile');
   		$userValue['sns_type'] = $this->input->post('sns_type');

		$userInfo = $this->UserModel->getUserInfoIfUserExist($userValue['user_id']);
		if($userInfo == null){
			$userInfo = $this->UserModel->addUserWithSNS($userValue);	
		}
		$this->output->set_header('Content-Type: application/json; charset=utf-8');
		echo json_encode($userInfo); 
	}
}
?>
