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
    function getContentsList($userId, $contentsType){
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
    function getFavoriteContentsList($userId, $contentsType){
        $EXHIBITION=1;
        $PLAY=2;
        if($contentsType == $EXHIBITION){
            $this->db->select('hb.exhibition_id, hb.exhibition_code, hb.exhibition_title, hb.exhibition_artist, hb.start_date, hb.end_date, hb.exhibition_place, hb.exhibition_address, hb.exhibition_picture,hb.exhibition_price_adult, hb.exhibition_price_student, hb.exhibition_price_children, hb.exhibition_price_old_infirm,hb.exhibition_price_special, hb.exhibition_call, hb.exhibition_site');
            $this->db->from('favorite_contents as fc');
            $this->db->join('exhibition as hb','hb.exhibition_id =fc.contents_id');
            $this->db->where('fc.user_id',$userId);
            $this->db->order_by('hb.exhibition_id','DESC');
            $query = $this->db->get();
            $pos = 0;
            if($query->num_rows()==0)
                return null;
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
        } else if($contentsType == $PLAY){
            $this->db->select('hb.play_id, hb.play_code, hb.play_title, hb.play_actors, hb.start_date, hb.end_date, hb.play_place, hb.play_address, hb.play_picture,hb.play_price_adult, hb.play_price_student, hb.play_price_children, hb.play_price_old_infirm,hb.play_price_special, hb.play_call, hb.play_site');
            $this->db->from('favorite_contents as fc');
            $this->db->join('play as hb','hb.play_id =fc.contents_id');
            $this->db->where('fc.user_id',$userId);
            $this->db->order_by('hb.play_id','DESC');

            $query = $this->db->get();
            $pos = 0;
            if($query->num_rows()==0)
                return null;
            foreach($query->result() as $row){
                $data[$pos]['play_id'] = $row->play_id;
                $data[$pos]['play_code'] = $row->play_code;
                $data[$pos]['play_title'] = $row->play_title;
                $data[$pos]['play_actors'] = $row->play_actors;
                $data[$pos]['start_date'] = $this->changeDateTimeFormat($row->start_date);
                $data[$pos]['end_date'] = $this->changeDateTimeFormat($row->end_date);
                $data[$pos]['play_place'] = $row->play_place;
                $data[$pos]['play_address'] = $row->play_address;
                $data[$pos]['play_picture'] = $row->play_picture;
                $data[$pos]['play_price_adult'] = $row->play_price_adult;
                $data[$pos]['play_price_student'] = $row->play_price_student;
                $data[$pos]['play_price_children'] = $row->play_price_children;
                $data[$pos]['play_price_old_infirm'] = $row->play_price_old_infirm;
                $data[$pos]['play_price_special'] = $row->play_price_special;
                $data[$pos]['play_call'] = $row->play_call;
                $data[$pos++]['play_site'] = $row->play_site;
            }
            return $data;
        }




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
    }
}
?>