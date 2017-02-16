<?php
class UserModel extends CI_Model {

    function __construct(){
        parent::__construct();
        $this->load->database();
        $this->load->helper('date');
    }
    
    function changeDateTimeFormat($date){
        $date = strtotime($date);
        $date = date('y.m.d',$date);
        return $date;
    }

    function getUserInfoIfUserExist($userId){
        $this->db->select('user_id, user_name, user_email, user_profile, sns_type');
        $this->db->from('user');
        $this->db->where('user_id',$userId);
        $query = $this->db->get();
        if($query->num_rows()==0) // new user
            return null;
        $row = $query->row();
        $user['user_id'] = $row->user_id;
        $user['user_name'] = $row->user_name;
        $user['user_email'] = $row->user_email;
        $user['user_profile'] = $row->user_profile;
        $user['sns_type'] = $row->sns_type;
        return $user;
    }

    function addUserWithSNS($data){
        $user = array(
            'user_id' => $data['user_id'],
            'user_name' => $data['user_name'],
            'user_email' => $data['user_email'],
            'user_profile' => $data['user_profile'],
            'sns_type' => $data['sns_type']
        );
        $this->db->insert('user', $user);
        return $user;
    }
}
?>