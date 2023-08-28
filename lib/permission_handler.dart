import 'package:permission_handler/permission_handler.dart';

class PermissionHandlerClass{

  Future<bool> permissionSensor() async {
    bool result = true;
    var status = await Permission.sensors.status;
    if(!status.isGranted){
      PermissionStatus resultP = await Permission.sensors.request();
      if(!resultP.isGranted){
        result = false;
        await openAppSettings();
      }
    }
    return result;
  }

}

