<?php
class ContentsModel extends CI_Model {

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

    function getFavoriteContentsList($userId){
        $this->db->select('user_id, contents_id, contents_type');
        $this->db->from('favorite_contents');
        $this->db->where('user_id',$userId);
        $query = $this->db->get();
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['user_id'] = $row->user_id;
            $data[$pos]['contents_id'] = $row->contents_id;
            $data[$pos++]['contents_type'] = $row->contents_type;
        }
        return $data;
    }

    function getFavoriteContentsListByContentsType($userId, $contentsType){
        $this->db->select('user_id, contents_id, contents_type');
        $this->db->from('favorite_contents');
        $this->db->where('user_id',$userId);
        $this->db->where('contents_type',$contentsType);
        $query = $this->db->get();
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['user_id'] = $row->user_id;
            $data[$pos]['contents_id'] = $row->contents_id;
            $data[$pos++]['content_type'] = $row->contents_type;
        }
        return $data;
    }

    function registerFavoriteContents($data){
        $favoriteContents = array(
            'user_id' => $data['user_id'],
            'contents_id' => $data['contents_id'],
            'contents_type' => $data['contents_type']
        );
        $this->db->insert('favorite_contents', $favoriteContents);
        return $favoriteContents;
    }
    
    function deleteFavoriteContents($data){
        $this->db->where('user_id', $data['user_id']);
        $this->db->where('contents_id', $data['contents_id']);
        $this->db->where('contents_type', $data['contents_type']);
        $this->db->delete('favorite_contents'); 
        return $data;
    }
}
?>