<?php
class MapModel extends CI_Model {
    function __construct(){
        parent::__construct();
        $this->load->database();
    }

    function getLatLngFromAddress($address){
        $client_id = "IIUrL9SHY62I8qM3OdtT";
        $client_secret = "0FN4pPMj4Z";
        $encText = urlencode($address);
        $url = "https://openapi.naver.com/v1/map/geocode?query=".$encText;

        $is_post = false;
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, $is_post);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $headers = array();
        $headers[] = "X-Naver-Client-Id: ".$client_id;
        $headers[] = "X-Naver-Client-Secret: ".$client_secret;
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        $response = curl_exec ($ch);
        $status_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close ($ch);
        if($status_code == 200) {
            return $response;
        } else {
            return null;
        }
    }
}
?>