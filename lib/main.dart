import 'dart:typed_data';

import 'package:animated_button/animated_button.dart';
import 'package:flutter/material.dart';
import 'package:hid_pro4/permission_handler.dart';

import 'metholChannel.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'HID',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  //METODOS
  void startScan() async {
    Map<dynamic, dynamic> result = await AndroidChannel.startScan();
    int status = result['status'];
    print('status: $status');
    Uint8List img = result['img'];
    print('Uint8List: $img');
    String errorMessage = result['errorMessage'];
    print('Error: $errorMessage');
    // Procesar los resultados del escaneo aquí
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: AnimatedButton(
          onPressed: () => startScan(),
          enabled: true,
          child: Icon(
            Icons.fingerprint_outlined,
            color: Colors.white,
            size: 45,
          ),
        ),
        // Column(
        //   mainAxisAlignment: MainAxisAlignment.center,
        //   mainAxisSize: MainAxisSize.max,
        //   children: [
        //     ElevatedButton(
        //       style: ButtonStyle(
        //         elevation: MaterialStatePropertyAll(10),
        //         shadowColor: MaterialStatePropertyAll(Colors.black),
        //         //fixedSize: MaterialStatePropertyAll(Size(35, 35)),
        //
        //       ),
        //       onPressed: () => startScan(),
        //       child: const Icon(Icons.fingerprint_outlined),
        //     ),
        //   ],
        // ),
      ),
    );
  }
}
