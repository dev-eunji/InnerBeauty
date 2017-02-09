<?php
class ExhibitionModel extends CI_Model {

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
    function getExhibitionInfo($exhibitionId){
        $this->db->select('hb.exhibition_id, hb.exhibition_code, hb.exhibition_title, hb.exhibition_artist, hb.start_date, hb.end_date, hb.exhibition_place, hb.exhibition_address, hb.exhibition_picture,hb.exhibition_price_adult, hb.exhibition_price_student, hb.exhibition_price_children, hb.exhibition_price_old_infirm,hb.exhibition_price_special, hb.exhibition_call, hb.exhibition_site');
        $this->db->from('exhibition as hb');
        $this->db->where('hb.exhibition_id',$exhibitionId);
        $query = $this->db->get();
        $pos = 0;
        //$data = array();
        foreach($query->result() as $row){
            $data[$pos]['exhibition_id'] = $row->exhibition_id;
            $data[$pos]['exhibition_code'] = $row->exhibition_code;
            $data[$pos]['exhibition_title'] = $row->exhibition_title;
            $data[$pos]['exhibition_artist'] = $row->exhibition_artist;
            $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
            $data[$pos]['exhibition_place'] = $row->exhibition_place;
            $data[$pos]['exhibition_address'] = $row->exhibition_address;
            $data[$pos]['exhibition_picture'] = $row->exhibition_picture;
            $data[$pos]['exhibition_price_adult'] = $row->start_date;
            $data[$pos]['exhibition_price_student'] = $row->exhibition_price_student;
            $data[$pos]['exhibition_price_children'] = $row->exhibition_price_children;
            $data[$pos]['exhibition_price_old_infirm'] = $row->exhibition_price_old_infirm;
            $data[$pos]['exhibition_price_special'] = $row->exhibition_price_special;
            $data[$pos]['exhibition_call'] = $row->exhibition_call;
            $data[$pos++]['exhibition_site'] = $row->exhibition_site;
        }
        return $data;
    }

    function getExhibitionList($page){
        $this->db->select('hb.exhibition_id, hb.exhibition_code, hb.exhibition_title, hb.exhibition_artist, hb.start_date, hb.end_date, hb.exhibition_place, hb.exhibition_address, hb.exhibition_picture,hb.exhibition_price_adult, hb.exhibition_price_student, hb.exhibition_price_children, hb.exhibition_price_old_infirm,hb.exhibition_price_special, hb.exhibition_call, hb.exhibition_site');
        $this->db->from('exhibition as hb');
        $this->db->order_by('hb.exhibition_id','DESC');
        //$this->db->limit(2,$page*2);
        $query = $this->db->get();
        $pos = 0;
        foreach($query->result() as $row){
            $data[$pos]['exhibition_id'] = $row->exhibition_id;
            $data[$pos]['exhibition_code'] = $row->exhibition_code;
            $data[$pos]['exhibition_title'] = $row->exhibition_title;
            $data[$pos]['exhibition_artist'] = $row->exhibition_artist;
            $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
            $data[$pos]['exhibition_place'] = $row->exhibition_place;
            $data[$pos]['exhibition_address'] = $row->exhibition_address;
            $data[$pos]['exhibition_picture'] = $row->exhibition_picture;
            $data[$pos]['exhibition_price_adult'] = $row->exhibition_price_adult;
            $data[$pos]['exhibition_price_student'] = $row->exhibition_price_student;
            $data[$pos]['exhibition_price_children'] = $row->exhibition_price_children;
            $data[$pos]['exhibition_price_old_infirm'] = $row->exhibition_price_old_infirm;
            $data[$pos]['exhibition_price_special'] = $row->exhibition_price_special;
            $data[$pos]['exhibition_call'] = $row->exhibition_call;
            $data[$pos++]['exhibition_site'] = $row->exhibition_site;
        }
        return $data;
    }

    function test($page){
            $this->db->select('hb.start_date');
            $this->db->from('exhibition as hb');
            $query = $this->db->get();
            $pos=0;
            foreach($query->result() as $row){
                $data[$pos++]['start_date'] = $this->changeDateTimeFormat($row->start_date);
            }
            return $data;
    }

}
?>