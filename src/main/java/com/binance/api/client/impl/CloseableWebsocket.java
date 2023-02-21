package com.binance.api.client.impl;

import okhttp3.WebSocket;

import java.io.Closeable;
import java.io.IOException;

public class CloseableWebsocket implements Closeable {

  private WebSocket webSocket;
  BinanceApiWebSocketListener<?> listener;

  public CloseableWebsocket(WebSocket webSocket, BinanceApiWebSocketListener<?> listener) {
    this.webSocket = webSocket;
    this.listener = listener;
  }

  @Override
  public void close() throws IOException {
    final int code = 1000;
    listener.onClosing(webSocket, code, null);
    webSocket.close(code, null);
    listener.onClosed(webSocket, code, "closed");
  }
}
