<?php
$file = $_FILES['file'];
$tmp_name = $file['tmp'];
$tmp = getimagesize($tmp_name);
$img = $extension = null;
switch ($tmp_size[2]){
    case 1:
        $img = imageCreateFromGIF($tmp_name);
        $extension = 'gif';
        break;
    case 2:
        $img = imageCreateFromJPEG($tmp_name);
        $extension = 'jpg';
        break;
    case 3:
        $img = imageCreateFromPNG($tmp_name);
        $extension = 'png';
        break;
    default : break;
}
$save_dir = '../image/uploaded/';
$save_filename = date('YmdHis');
$save_basename = $save_filename.'.'.$extension;
while(file_exists($save_path)){
    $save_filename .=mt_rand(0,9);
    $save_basename = $save_filename.'.'.$extension;
    $save_path = $_SERVER["DOCUMENT_ROOT"].$save_dir.$save_basename;
}

$img_size = getImageSize($img, 2000);
$out = imageCreateTrueColor($img_size['w1'], $img_size['h1']);

function getImageSize($img = null, $mazsize = 500){
    if(!$img) return false;
    $w0 = $w1 = imageSx($img);
    $h0 = $h1 = imageSy($img)
    if($w0 > $maxsize){
        $w1 = $maxsize;
        $h1 = (int)$h0 * ($maxsize / $w0);
    }
    if($h1 > $maxsize){
        $w1 = (int)$w1 * ($maxsize / $h1);
        $h1 = $maxsize;
    }
    return array(
        'w0'=>$w0,
        'h0'=>$h0,
        'w1'=>$w1,
        'h1'=>$h1,
    );
}
$color_write = imageColorAllocate($out,255,255,255);
imageFill($out,0,0,$color_write);

imageCopyResampled(
    $out,
    $img,
    0,0,0,0,
    $img_size['w1'],$img_size['h1'],$img_size['w0'],$img_size['h0']
);
saveImage($out,$save_path,$extension);

function saveImage($img = null, $file = null, $ext = null){
    if(!$img || !$file || !$ext)return false;
    switch($ext){
        case"jpg":
            $result = imageJPEG($img,$file);
            break;
        case"gif":
            $result = imageGIF($img,$file);
            break;
        case"png":
            $result = imagePNG($img,$file);
            break;
        default : return dalse; break;
    }
    chmod($file,0644);
    return $result;
}
?>
