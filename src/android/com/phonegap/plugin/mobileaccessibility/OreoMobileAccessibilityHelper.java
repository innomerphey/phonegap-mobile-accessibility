/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package com.phonegap.plugin.mobileaccessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.widget.Toast;
import android.content.res.Configuration;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.content.Context;

@TargetApi(26)
public class OreoMobileAccessibilityHelper extends
    KitKatMobileAccessibilityHelper {
  private Context mContext;
  @Override
  public void initialize(MobileAccessibility mobileAccessibility) {
    super.initialize(mobileAccessibility);
    mContext = mobileAccessibility.cordova.getActivity().getBaseContext();
  }
  @Override
  public void disableDisplayZoom() {
    try {
      Configuration configuration = mContext.getResources().getConfiguration();
      configuration.fontScale = (float) 1; //0.85 small size, 1 normal size, 1,15 big etc
      DisplayMetrics metrics = new DisplayMetrics();
      ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
      metrics.scaledDensity = configuration.fontScale * metrics.density;
      configuration.densityDpi = (int) mContext.getResources().getDisplayMetrics().xdpi;
      mContext.getResources().updateConfiguration(configuration, metrics);
      Toast.makeText(mContext, String.valueOf(metrics.scaledDensity) +  " | " + String.valueOf(configuration.densityDpi), Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Toast.makeText(mContext, "disableDisplayZoom: " + e.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}
