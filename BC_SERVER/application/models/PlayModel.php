<?php
class PlayModel extends CI_Model {

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
    function getPlayInfo($playId){
        $this->db->select('hb.play_id, hb.play_code, hb.play_title, hb.play_actors, hb.start_date, hb.end_date, hb.play_time, hb.play_run_time, hb.play_place, hb.play_address, hb.play_picture,hb.play_price_adult, hb.play_price_student, hb.play_price_children, hb.play_price_old_infirm,hb.play_price, hb.play_call, hb.play_site, hb.play_detail_info, hb.play_ticket_site1, hb.play_ticket_site2');
        $this->db->from('play as hb');
        $this->db->where('hb.play_id',$playId);
        $query = $this->db->get();
        $pos = 0;
        //$data = array();
        foreach($query->result() as $row){
            $data[$pos]['play_id'] = $row->play_id;
            $data[$pos]['play_code'] = $row->play_code;
            $data[$pos]['play_title'] = $row->play_title;
            $data[$pos]['play_actors'] = $row->play_actors;
            $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
            $data[$pos]['play_time'] = $row->play_time;
            $data[$pos]['play_run_time'] = $row->play_run_time;
            $data[$pos]['play_place'] = $row->play_place;
            $data[$pos]['play_address'] = $row->play_address;
            $data[$pos]['play_picture'] = $row->play_picture;
            $data[$pos]['play_price_adult'] = $row->play_price_adult;
            $data[$pos]['play_price_student'] = $row->play_price_student;
            $data[$pos]['play_price_children'] = $row->play_price_children;
            $data[$pos]['play_price_old_infirm'] = $row->play_price_old_infirm;
            $data[$pos]['play_price'] = $row->play_price;
            $data[$pos]['play_call'] = $row->play_call;
            $data[$pos]['play_site'] = $row->play_site;
            $data[$pos]['play_detail_info'] = $row->play_detail_info;
            $data[$pos]['play_ticket_site1'] = $row->play_ticket_site1;
            $data[$pos++]['play_ticket_site2'] = $row->play_ticket_site2;
        }
        return $data[0];
    }

    function getplayList($page){
        $this->db->select('hb.play_id, hb.play_code, hb.play_title, hb.play_actors, hb.start_date, hb.end_date, hb.play_time, hb.play_run_time, hb.play_place, hb.play_address, hb.play_picture,hb.play_price_adult, hb.play_price_student, hb.play_price_children, hb.play_price_old_infirm,hb.play_price, hb.play_call, hb.play_site, hb.play_detail_info, hb.play_ticket_site1, hb.play_ticket_site2');
        $this->db->from('play as hb');
        $this->db->order_by('hb.play_id','DESC');
        //$this->db->limit(2,$page*2);
        $query = $this->db->get();
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['play_id'] = $row->play_id;
            $data[$pos]['play_code'] = $row->play_code;
            $data[$pos]['play_title'] = $row->play_title;
            $data[$pos]['play_actors'] = $row->play_actors;
            $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
            $data[$pos]['play_time'] = $row->play_time;
            $data[$pos]['play_run_time'] = $row->play_run_time;
            $data[$pos]['play_place'] = $row->play_place;
            $data[$pos]['play_address'] = $row->play_address;
            $data[$pos]['play_picture'] = $row->play_picture;
            $data[$pos]['play_price_adult'] = $row->play_price_adult;
            $data[$pos]['play_price_student'] = $row->play_price_student;
            $data[$pos]['play_price_children'] = $row->play_price_children;
            $data[$pos]['play_price_old_infirm'] = $row->play_price_old_infirm;
            $data[$pos]['play_price'] = $row->play_price;
            $data[$pos]['play_call'] = $row->play_call;
            $data[$pos]['play_site'] = $row->play_site;
            $data[$pos]['play_detail_info'] = $row->play_detail_info;
            $data[$pos]['play_ticket_site1'] = $row->play_ticket_site1;
            $data[$pos++]['play_ticket_site2'] = $row->play_ticket_site2;
        }
        return $data;
    }
    
    function addClickNumToPlay($playId){
        $this->db->set('play_clicked', 'play_clicked+1', FALSE);
        $this->db->where('play_id', $playId);
        $result = $this->db->update('play'); 
        return $result;
    }

    function getGlobalFavoritePlay(){
        $this->db->select('hb.play_id, hb.play_code, hb.play_title, hb.play_actors, hb.start_date, hb.end_date, hb.play_time, hb.play_run_time, hb.play_place, hb.play_address, hb.play_picture,hb.play_price_adult, hb.play_price_student, hb.play_price_children, hb.play_price_old_infirm,hb.play_price, hb.play_call, hb.play_site, hb.play_detail_info, hb.play_ticket_site1, hb.play_ticket_site2');
        $this->db->from('play as hb');
        $this->db->order_by('hb.play_clicked', 'DESC');
        $query = $this->db->get();
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['play_id'] = $row->play_id;
            $data[$pos]['play_code'] = $row->play_code;
            $data[$pos]['play_title'] = $row->play_title;
            $data[$pos]['play_actors'] = $row->play_actors;
            $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
            $data[$pos]['play_time'] = $row->play_time;
            $data[$pos]['play_run_time'] = $row->play_run_time;
            $data[$pos]['play_place'] = $row->play_place;
            $data[$pos]['play_address'] = $row->play_address;
            $data[$pos]['play_picture'] = $row->play_picture;
            $data[$pos]['play_price_adult'] = $row->play_price_adult;
            $data[$pos]['play_price_student'] = $row->play_price_student;
            $data[$pos]['play_price_children'] = $row->play_price_children;
            $data[$pos]['play_price_old_infirm'] = $row->play_price_old_infirm;
            $data[$pos]['play_price'] = $row->play_price;
            $data[$pos]['play_call'] = $row->play_call;
            $data[$pos]['play_site'] = $row->play_site;
            $data[$pos]['play_detail_info'] = $row->play_detail_info;
            $data[$pos]['play_ticket_site1'] = $row->play_ticket_site1;
            $data[$pos++]['play_ticket_site2'] = $row->play_ticket_site2;
        }
        return $data;
    }
}
?>