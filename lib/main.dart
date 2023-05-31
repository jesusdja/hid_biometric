import 'dart:typed_data';

import 'package:flutter/material.dart';

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
      appBar: AppBar(),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () => startScan(),
              child: const Icon(Icons.fingerprint_outlined),
            ),
          ],
        ),
      ),
    );
  }
}
