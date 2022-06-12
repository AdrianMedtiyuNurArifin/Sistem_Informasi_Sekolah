<?php
require_once 'DBCon.php';

//array untuk menampilkan respon
$response = array();

if (isset($_GET['apicall'])) {
    switch ($_GET['apicall']) {
        case 'signup':
            //mengecek parameter
            if (checkParameter(array('username','password', 'nama', 'akses'))) {
                //mendapatkan value
                $username = $_POST['username'];
                $password = $_POST['password'];
                $nama = $_POST['nama'];
                $akses = $_POST['akses'];

                //mengecek apakah username telah dipakai
                $stmt = $conn->prepare("SELECT id FROM user WHERE username = ?");
                $stmt -> bind_param("s", $username);
                $stmt -> execute();
                $stmt ->store_result();

                if ($stmt->num_rows > 0) {
                    $response['message'] = "Username telah terdaftar";
                    $stmt->close();
                }else { //jika username belum dipakai
                    $stmt = $conn->prepare("INSERT INTO user (username, password, nama, akses) VALUES (?, ?, ?, ?)");
                    $stmt->bind_param("ssss", $username, $password, $nama, $akses);

                    if ($stmt->execute()) {
                        $stmt = $conn->prepare("SELECT id, id, username, password, nama, akses FROM user WHERE username = ?");
                        $stmt->bind_param("s", $username);
                        $stmt->execute();
                        $stmt->bind_result($userid, $id, $username, $email, $nama, $akses);
                        $stmt->fetch();

                        $user = array(
                            'id'=>$id,
                            'username'=>$username,
                            'password'=>$password,
                            'nama'=>$nama,
                            'akses'=>$akses
                        );

                        $stmt->close();

                        $response['error'] = false;
                        $response['message'] = 'User sukses didaftarkan';
                        $response['user'] = $user;
                    }
                }
            }else {
                $response['error'] = true;
                $response['message'] = 'Harap isi semua data';
            }
            break;
        
        case 'login':
            //mengecek parameter
            if (checkParameter(array('username', 'password'))) {
                $username = $_POST['username'];
                $password = $_POST['password'];

                $stmt = $conn->prepare("SELECT id, username, nama, akses FROM user WHERE username = ? AND password = ?");
                $stmt->bind_param("ss", $username, $password);

                $stmt->execute();
                $stmt->store_result();

                if ($stmt->num_rows > 0) {
                    $stmt->bind_result($id, $username, $nama, $akses);
                    $stmt->fetch();

                    $user = array(
                        'id'=>$id,
                        'username'=>$username,
                        'nama'=>$nama,
                        'akses'=>$akses
                    );

                    // $response['error'] = false;
                    $response['message'] = 'Login Berhasil';
                    $response['user'] = $user;

                    $response['username'] = $username;
                    $response['akses'] = $akses;

                }else {
                    // $response['error'] = true;
                    // $response['message'] = 'Username atau Password salah';
                }
            }
            break;
        
        case 'getPengumuman':
            // $stmt = $conn->prepare("SELECT * FROM pengumuman");
            $read = "SELECT * FROM pengumuman";
            $res = mysqli_query($conn, $read);
            $num = mysqli_num_rows($res);

            $json = array();
            if ($num > 0) {
                while ($obj=mysqli_fetch_object($res)) {
                    $json[] = $obj;
                }
            }
            header("Content-Type: application/json");
            echo json_encode($json);

            // $result = array();
            // while ($row = mysqli_fetch_array($res)) {
            //     array_push($result, array('id'=>$row[0], 'judul'=>$row[1], 'isi'=>$row[2]));
            // }
            // $json = json_encode(array($result));
            // echo ($json);
            // $json = str_replace("\u0022","\\\\\"",json_encode( $result,JSON_HEX_QUOT));
            // echo $json;
            // echo htmlspecialchars(json_encode($result), ENT_QUOTES, 'UTF-8');

            exit(0);

        default:
            $response['message'] = 'Invalid Operation Called';
            break;
    }
}

echo json_encode($response);
function checkParameter($params){
    foreach($params as $param){
        if (!isset($_POST[$param])) {
            return false;
        }
    }
    return true;
}
?>