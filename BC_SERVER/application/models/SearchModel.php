<?php
class SearchModel extends CI_Model {

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

    function searchContentsList($searchWord){
        $EXHIBITION_TYPE = 1;
        $PLAY_TYPE = 2;

        //$this->db->select('exhibition_id, exhibition_title');
        //$this->db->from('exhibition');
        //$this->db->like('exhibition_title', binary($searchWord), 'both'); 
        //$this->db->order_by('exhibition_id','DESC');
        //$query = $this->db->get();
        $query = $this->db->query("select exhibition_id, exhibition_title from exhibition where binary(exhibition_title) like '%"+$searchWord+"%'");

        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['contents_id'] = $row->exhibition_id;
            $data[$pos++]['contents_type'] = $EXHIBITION_TYPE;
        }

        //$this->db->select('play_id, play_title');
        //$this->db->from('play');
        //$this->db->like('play_title', $searchWord, 'both'); 
        //$this->db->order_by('play_id','DESC');
        //$query = $this->db->get();
        $query = $this->db->query("select play_id, play_title from play where binary(exhibition_title) like '%" + $searchWord + "%'");
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['contents_id'] = $row->play_id;
            $data[$pos++]['contents_type'] = $PLAY_TYPE;
        }
        return $data;
    }
}
?>