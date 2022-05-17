# FreshPIC
FreshWorks challenge app

# Core
The app is build in a multi-module project, with a CLEAN Architecture from a global perspective and a MVVM architechture for the View-UI layer

# Used Technologies
- Android
- Hilt
- Retrofit 2
- Room
- Proto DataStore
- Glide
- JUnit 4
- Mockito
- Coroutines
- Navigation Component
- Material Components
- Stetho

# Main Functionalities
- Fetch all the trending GIFs from API (I took a risk here :D I wanted to make two independent screens, with different look and feel and aslo different functionalities)
- Fetch a list of GIFs based on a query
- Mark GIFs as Favorites and also have them displayed in an independent screen
- Age Control (I noticed the Giphy API receives an argument called rating which determines how explicit the results are, so in the first install the app asks the user if is adult or not)
- Day/Night theme support ✨
- Screen orientation support

# Screenshots
## Dark Theme

![alt text](https://lh3.googleusercontent.com/QwxDI5kHMGodsHZ45c8LH_5P1yHJhx9iPAudbkKGvaU38m7uIXEtGrLBYOt_7XYxj62mzPuNQx5mbdRnfNuuyw-Pff5wxFxUbCMdAutnZ-yPH_iFMkMzohTDF9gG5Ik5vj_icuMmqSf_PKVLOWIf4fSh3upMsiEwAdXIzqM3R9ODXQjUYVfiLKBOH-K0rubjVNBzzjAdrPj6ZzBmYZXag5aC5Dwcv4VdM4ofi5bNIhN6eViNjRORmFPrde2ulY0RcqT5xqYUPAAgO4yv_ogQ66HWueh-4OGuxn3gfVxRlWXZrVDlAnr4dam3b10xb2C71O5hKsqU4-MML1W2VmCZVdclVlWErdY_A17isOHpJbWTTN2JH49J_Glbq276VxQ6R1G_EYIs2J7iPZoYprb8JOU1DeudiKJAezBGGjw88xVsaY5N7cZ9Yj8y2wWLN8CqfXcEN7ltET4MNNNQLzs00bmhUfYCTqFAxBEhIXiamAElh6KLNFccQyT_BCylXncxccaKR5LhEtHwuNhPr3t6YJP1olgdFtASVHnz7yDlSYYXEgivMZYKn4Ltn5cTdq6sj0U2j2Qk67yRIdC7R5jmW0gwOlp2VGf9u-gWOYamagEKOoRaK3rzsOIteEsSuIE-P69gAYnaF90_AJdVAdExOZt6HsloDJHgzaLl1dEWXaBjosA_UjNqq08ebFQfjaIwiNKzDgGpraqK5NxceB80NNTDabHqUbdR3KRsNq9ImiyaRS9vW4P5SK5NA-5PwFKyT_9DoUi35B9uTZBK-D8o0FiqpE8odqvkp2_P=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/iMgE4DS4dSFrsh4N3_p6KHkXj_35chfK23QqmCXEkBg_LhYF1_yM-OCdnieqqyWovRP1jSDB0NZRYSOogOwykNKVrfL5VlL7-RvfALLYsN8dn137nGaEV9LeZfJ46r_8uAeEG7TwT6bcgV7IbS0f282p9T1dHfcm2Fmn7wPYY9v74GkIdqExwMLWi7QDFIJVWKkAG90v9OGVkNnbjj4J1cdzcotLxZmCnbtnR9jxZuNsHQuhQnAgpsfOTdxli3JauZO-pMI1adrincyZTFVqSK7FcOmdk403ooLReGWmWb_IcuE2bDyPUZtn7eEaSVZteoz4vW0ns4LtkIzdsWSbHUAWWDoUGG7HXbTSOwJKud9qEfWw5j3khqAnWDcngkMYtv_VDW0lp1ZKEV8mBftJavkusQeu_aa5v_wgK5BdVs1kM37JkLhf2IJucq1Wkoxhc1_8VmmUwWJjo6rCjThcL8uMSLSejzvi8sXDv7H3Cdm0ewtcdUu0FFp43fpN16H8dsP5s7AGm-3rCznGoH5S5KQuLIF8csbLCtLuLLJpOU9zDeFIXpEL0aAsjacw0-mflcKu9A26gAoy0lCrqRwqlaNKl6vYQnfIFbgUa0wC0EPzmh8J0X3wV_L47-gu4aZEmmTdN14058afzH9cYHRSz8o5GIM9DKIuzxV7IHQYZZm2hmH1lYe4fA7yimLZAzHZonjUld7j1tdhkZSYqpKxEZK2Rf0lGwxd2yDTogkDGgdceEdyrFEstc1uh59TvomjjuaKYk4qn6lITfHQRHgrcS9C33iyg27jwPsK=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/AJcVPk2ducX1kXM_NOsty4YbloivdBW8NZoVw1dm0Bzfad3gBWl3KoArO1v_41RRpM9_kBKrZABG1J5ttATmN_WPC-4rAu0RwUDio8HnMvjhla5iCCltTCatpxpOu5ir_FLhgXDfMlLt1gXORMi8IKKqr6VdSDLgGrKZ8g1mIMImnqgbDgeT1XBUyATl7BTCCc1BCbPQcjKezhctvmiK754V3S_tL3r8trA4sqSLN1ctDTjMGuVeFsetW5DNu6Dvx7gI-4o8hC_F5DOS8UHT5iSryVNBrxaRImduwDQl4j0bOpRZnE1vfX4WMqf1Q-FV53G8bWaQEX8yg6GQ7QJKjgg076UuiCY_4VAM0Ph0Lr0ZPO6NDwbbn2DECX0h8g6NlJcCac5VZFYAbnVqkX0nkUwX4tdjxLncyD98YM_Q6cBTsI3OllTJ_MEbx4VhUj33_8IRy2npF8nsnEfEaTAAlNnR2VVjwxaHvfJr70sbmklXXEWXFd8X5RAMXjus4MrUKkAvFLCKtAyY0l8P13YCHQHHQkBVR5i5UTRmVb203iok4ZYoksxYEAtxKM4mqwjfBtExAS46VCC9XhImwvnanefLGsqgLeKoDC7Hc7DLrm3FJsZwxM9brMD_wgDg8krZaPFx7xbzab21Yut3-Vxd180izlwod4KFfcxpXPEqPDROq2G73ZAsfg_9I-BXTGCiRk__cEs0VoaTkYDhphI8rVe1SmVhgAvYzCNlsRsCBTwDxyGcGMzUpwjqV3d7nWaZSP0yf3aE-jEh3iTw1oxTFU2YrMT42ginHVDs=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/rVkUVgXOm_Xz53GoN0oRQLcCoqYPeW6v8R4pHjE1ITnUnG2b60zt2IuZfy_4GZatM8JC6Lgg-pSkmxocEKD-xiQqK1H6Q6z_UNrrFGC4BLWUemVXYScxTZ9TaIQZ7vN2XfPlfwvsbS7FkffiZ6D3WP2KuBAW8uyjIanzPTvSCZUhA0pS5hk9UL-417A0bftVOSzMb_LTPnR5uoYXlX307siOCL2XMp_lUoR9OcJO13w-NTzOJew70poHJCdxFXbGYdwcHd4spdE2jgDN0BsDYfZ13mxokUe8oGbDunKjVn76L6d9dRDvE2gnfoDKfEVQPwE6vNv5L10ESqUZW1YU8-8d6PKelLTgW4em9ab3fQXb48SXT2CjFjfDPBLBCWlprQQ173Wv4u50mWyzlLM3oAnfemG5PA6v7Q1mxvOC0nyLRjIukhkbBM3MBHLqJ2QwYzPSFvTkcND7cYj0aMS7ieTippKIz90TwB0G4Z6rtpZS7pfYXVOuKuniI1qpEZscslniJr-lcrdghGsxWsd6EqvE2O1QZGxLWAl-NJIA2x4KYLowdcaLHYSFxM028eK5jc37U8htPKb4kGxbKk02EA-VasPDHGuEa1SGkJDFaqbzshC6IvQ8sdt53_p18pMiEd2QIbemYvIW4avzh31KnFtu_FYZlAPxl2uloXeNs1k17mBWG0Nn5iVrCECAepp9STtItVOZhCQjVTIfLIsEUZPjnMjjCrUMRhPqtwT4XRDZjJ9ftaOIV7j8Wom24KwyxiR2RLelD2fy5cNaE2o7R2o7UDItRJ5g34lq=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/1kc-o9a-SH_BnHAMd51vfJ0X7AuCxjyTjsxKNYpVsJ9zM5yAOBcJKsZc_vhlhQam3fO7iEwNjSDVhoyXfdSnNCEUNAhMF4wttr1_lCcFwaJKOuyKuEH0PiP55ALpi4APvbZi9fSA5wzHk0DQMZVljgDDOR2IBzzk-z3793F8E4ftB4fhSIuHO-w4Wd5DvmOlpueEpL1A1x1tqsLRD-7mhyvV0auTuxKtm8Mbbg024ylF0CsvJuOYR6CVkkdzm3IJ4W8BKmYX0A09rQXloeM6ozhtMcqKUCN8sOsgtxLWZxPi2rD0kTrCw6qHs6XG2gQdZZrse5laSsdwe-GubY59c18or4CnkFanTuAyTn6tmA4QNyPyItG9-TYP06OshN0cBqk991Bj2KXTl-sS8Kd_g0LjuVove4YNjmwGrJJN3bmbN9Vc7UJ5mXiKqM4_1JQds8Z-T6CY9mJhZe3byJ9hy6flc-1JW0h6bkUtGdWvIsYDI7OhFEf8l0hI_b4aqe1KLLLrehZUQ5L7mra00hh02unLGu53M3exeoTB0mFUDw7eMsrSMqnBSFhBiFV5_RdW7ciC4sAdiWM5yO9Ar83VaN-YsCzQuGV9QfTRLmLo5cbywkHjA_GEYg4Tj5D_1a2QHQoq7PshH_U8k5z3NCvHcZINCqWryVuBPJgF_6s5gw1N90hGcGMgAGXoAAkTciTL8G8YY5d_xzxks5Hq-O_iDNwye-IyPc4-GSLVk1fGo9mBfMHMgiSLCMWTmQMmOce0NodUBo0aiRkQIugvFVx4GVtBL8dQwFZRXBQP=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/5oOzXYaclqLHPKJA6LKH6MevmyyqFx1r5LjidmqVl3pJvSQ12Rdj4Ty3BRAGOMYz6RcUnPa_OK-5qzFA1nD2YGJCXVNlvVBO63sQ51XrotjH2q7aXFZepO1EZwTDgUofKI39INR08YANMYpvuAmp7FBa3MgrbvF3eakxBNBB31LwUdPvfvERwu06AXemsULiUB04NgMw0SZm1xaeSCEGW4m_uNk_UhRxCe2KL1Qg8B_nXq2via9l1N92uFA7B3NB1J4Vf-BxMjYjtK-V4hdorDODI6ivrOwer4Yw0m3GFqezc0FtpUosNSiEAGezlYAR8zY8cDkHoIbt8eXP42E97VXY0soPG48A-azGxHmWTHqgoFCFpIxBCQYCKeYkUzUj2xqCFOO6zz4WSybDr1ghBfv8gM_gZSf4eqn5B1LCD4_MCFf9AyVskqD66aN4bm4DzLbWqTAVauw2jFakEp8Fm9k7H0dHtpOpcYpjkOH1w7Sm8Hi6VnsvQltyU1-IxeHCpZ9IbgZQ0SBkRq4DaK5JXbGcJhO1Q21sQ58XsHM7U0aJOiFW8QcaCEXM9h1XmyYd6BlC1sMdsObY01fpsB8H6Z7WN859jqg-PkL8DyirfRRD0xUPmu8gDq61nGRVeo3N2EMHZXoZ7CCdDNgamrQqOkuxeMJwzh3fCVjgprUv_7RIB9BQniSn4raYd6JFr8t2_630mLObLGl2fd6kdTEP1vEc0B4jfNAlobzFkgDkii1WFpAZLG5dBd6zZi7FHyGzl4JrsoTCm8sqS2EqspYdUfmyCUYK6UHYWQ55=w401-h866-no?authuser=0)

## Light Theme

![alt text](https://lh3.googleusercontent.com/51IntZSfNS8PA_1MXhxnN_G_1fdFbeGbZaoB8luw2s1HjrBk_CoRANTONpNwOFeEcDCOEvtzwDjPjsUUniDqQD3-td3PlQy5R3e4NHcZEYsMedxpeYb0YmC6AayB2VjMBgtrN1GHMo1W0o8cuVQb8lINLfUHINGmvnpeUJo-M-6uvoMyazJBMZyjaNGZUZSqewVL1ThXS9ANu0_j5x-U8_rEy4Mtyn7YUXqQR2EbM1Jjr7VICBXVazwoYWnQBQdTk-w8QmiRLU_1i1Y8V0Jb48493BM79dSLgPZs8kEas1dCav9y6Qmgu-4Jwl23TSLRC7N_lV3xjk14jMxZ9JMWVY0C2RHcMdm3QDvyE2x3CFWYkX_tYEJFmxUXKM5Vl7hOyHmtIcx3sr72KSL_sH4TbtNnl9nYlQQoHU31TzTb57_IjPdeZ0qYeM9hEtIOqCMUraLPWZUS8xYgotkpxRywX3f9tAEevdFoZJaFE_k_s_iMNjVD8SdMV0GLsCcV10930qBroXPWZZB2iCXdX9UQBiqh4g7e1Df0C0FCemr5USK6iRMq9O6zAp4Ah6prCxjkiFBSOtpglEX7M6epXrCSl-6W2OFd_j8wJboKWFXHXuZZ6oXWsBAn_5QURoxMdcCkZgGr6HRlI_avlpKgH-WYgZYDoM32Ga6a1AiJ02HZxKQECpa5WTwfT7G-AJdORSkUGjnUmYp3bYgZXTLA5RaMj4F-Sf7n7JDYkkzOS2wEh0edFI9ZOnaNGPimOs2hAN7LWBW0tmo8S9LUXSSi7mH7J5KuiNTbp0CyzBdo=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/ZldXX69ANjIrHNXg0awXHEiO_uisRirpppK8I6twTBKYOBLXOh3fb6WP4o2phHF_3C_ovlNVW30JU4zRP0uiQKWpJU5DW5xbd024RDbOfdNq0GeosyBfbsNNO1n9vJwgRWo5tQEdg1Sa6-dtYphpjvtfBnW10A3qzsSd7FzPCTgw4Y1GHBjG2bF-Y-O84jld1krS-55vQSZZsCpx6rrQYS3cC1sBETuQCV6KI4XwHbtfErhV6_DP3apUirt0gCQ5oObhQRl0xweRfKHNur9jLJDpGa9DaBhT8RdcKBexIw4ZyJGIhOYfKF0eOwwbl9FGR0DoRljykfQhS2mxAVF4_ZM7kwYgKINLROgtz3UlnwfeeJYqeiWcHa00QmvKjLBbz8mi_449T1uyZ-7MNviIsDbaE2Lrpcaf0wNByiGYMLR-rAkephxATtmbTRtRWBpakVoRJYl8gHbtCRRzYj0ZWx91U8pGaPNyGPJUaTBAGqv6iH1-XcSDUpDHF_pDEKjQhnsPHfc9YV_2ITKsNED2TzjuYvW9y1LpPZmcGwmTcSOnwi-STfy_eje2sQIpLXsZW0XuuXlSbKVmjcI0ye6c4Lko9FBjhpuysJMOWdI3MIdSjWPPkqSm6608lL2Uuy9_N2TD4t6roftuJJKKr1DMbxeaxrXYX1s3cx2rl0jEzS44qTVOqtU9gihYxB1qtSoOg8GiXTXhM-FiV3-V-kX1DvP6NBRHVWZbtEoE4cmIdK9FthRxNf1AI8tkFP0gvJyL1gmhzGim2FUv6PqM_GdMj7jj7NroKYi9XW42=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/V-8kQzpit1XXKRD2QpNWIAQNr_QdMayKeI_VkzZRChZgZ0YzCHjquU_Ouqe23cmyIrMRFlAdJbgQOW1BAinV8oRq7gfj6z7f2QROy9T_-SOppk185aE4K6yYggo9xBLgwEWT0tsqwr1ZZvvYJYCM0GJ0moFjf2aDZJN6xrLHx70zJ_B8Xmh68ZijtckE7Ip0Rp1qOyEK05H0LomqdkRd5QTdCWzfXzliD3deCWSvhY97FU8h514M4TIxsP7IuVCKfGYNNsDPaOLdRa3SbMl-9AO0W0kvD4eK9cBSQBxqDZNmS6lTPVa39iiYJ71sLh6VtvLXRm0j8pNnXDn42xa4aggzomdYLcorkjiM5NcfrOL3H3wTbgwkgv93YHwh3NIWVy6muKMkyJpblQ84csurA9M44cXuoqFTbTEeRA7vZKVYWxJw57ViYHYXtxDZs3oIAaFbcZ05ESZi047vmSHv0UrHURzaBpYyB_39DR8x7Qgiu0U83TsusRkRBVfakAd0YEtqFLRuU10mSldsizew0BXqhZDc0UXXAuLwgsqofS4eHOzsrkJfN6IszvbUoX_8eofV45ROqg-84CFKAjKLoV_wFTD8k8t12Tq_2qJ-nKrjZup8shBqiX3l6jYtQXXl5JCigfibIKEe1IY5344TR92Jpq1CwAn8VfbbqP6teMp-1r-Hg5EaiMw2-pvr0aLLluQEDw-oPxDlrBdViL4_X-x0KoQPpW9MSwTH8FLcTuzR2wfKQVYxr8qJ-kMVkKeTm6ZLaZ2yduD4_hYVB7jLEM-4_1Xiyr8uoXTz=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/h58p6JEsXpijvdZiw13UUAwTmmmebPEHYdWMnx5WPQY2huxjyGPLFJ5SwgAgkcJ6zfCt0yjLCfbCLmrjh5_hfIVAQMeWz0JyauDQnX4y5dPY06fImKK6dnshF4B1GxaCH_14n2mp80QrR3vpIkZJKKmnVCTGpTyAqsXjkEnlMoeM7kDDxlZ8mpLZB7Nbu2EmJRdVwlOQPUgQi01_NWjH_qOXijbIbYkNEV5Ad8k5grWMn8sx2tqOw7LzbahsmU5UexJo3v8bnvdMZCIA7dcWnN3zhNTzFPnB49wJxQCgfDS6JUTk1Ivzg9lsVbAwdXUp6AR4F-cnFP6fDfwx1sK0Eytutif7Itj9JkV6ChQFFCjRob2ucy5ZCNwvIiVi3PY_1JVh9HLrRTLSqeIWKPRX-7RB6DqsRtJ4vAsI4xR31MzZAYgmRK2Ia_cz5DrZI6CLuWU5aDnEvNQe8FbelB6l33lQ2ww5Cf60M9LEEbVS-YZFVcd-xRsNJZO_NxA7QuNFHlq22UFB441RpDHhlPqGGNbRTOCzfBr_MrdqRiDOFL56_XID0Vx_aJDnnSbsRHWuA4EDYjPF2l-_6YNXULq6DO4cJkhy95pDNM03BE9nCFWXSZxcaA4KFNOMs23n8JgvctHCP1XhpVzXgkebMBJYeYBpGm2gbX0HhnP8UhOmZgSVk5yHTrdkzlFDmhhO8gN0LqJs89CdCWGzDSIAPnA6PX66hHEY-H6RRGqQ-BG1ZOeyy5aHg4Uy7_s9WVWUhvqRwWhuI8FFBiZWJc-6D0iRpvT5-3W__jBwgeZs=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/Z78g7fF9lHsNu3X-b3geZHMCh5tpFPRuIM5YRzK3M5tNZPCLqnw_Y6ALG-Y6kHn3L5vGAM7sfODa-AQSa7ep1Hd65wjlly5_61ofpFZ9G_ZcfT__hTGUigmTXAS_m2l6xFj1GMvbhqR81yyFy7s1f48KIoqEpcolB_Xzx-9QT3ADWpmlxxnzCimaQrmQMn78mu5u4Tphj-zfT_UoSUj84S5x_3v1YzkHXWHURgKYvAistqpPjkuKjUKTXrDtcVQGBWAmMb4U1hRbNimpweTr32MT5FF8z6bkUOiyo33oXn_GbUDld2psLuvM8k12-iraYg2fflegKf-o3HlcYkkh_Ansk3BAB9KGlOXqjx86SDRkA6OtTYUHdEHygepCau0e3fd1-eCW4xDryCPrSe2JYOnLPYLpqEpL4H1c0bT6YQkwizU-gqi80ymoYVC37W6vIq9scTbpTCfZrObadfnqf4VQnyJ_YLReeCjKON6KUsx2NSn-FcZ_Ozw0yfd1mxn0mrJRvO55eRq5DyG7222pt1UBbg29HfWlCc7N45D_ol_Q-lR4dNPECRb5I_ULigfOacu643dFyYRYdk_tVsTuk8BgUvJGqWFIPbrluop7nIkMPt_oemnNBh5tv4V7VFVx2KSqIOI0nsZgbN6StaDhmP93q0dNxsSLeEis7YPLk3Y7-G3LUrO3y7WVjLx190PPPC0KkG9ipM0yf89c3bn40PpG1GXMRoQPeQlpfOu4Vk13gPtoLl5pu3mQYOweMY9S9MB1lX-7nQx-q3TicX1N_xFYqQ-ck2Pqr20f=w401-h866-no?authuser=0)

![alt text](https://lh3.googleusercontent.com/ihGXosrnWc3eyFEqjfy6VhZi4nKEV31Gp2q6uZC6XtzKJmqjySd_SfZ7qt4ggDaEFn5SmdbvI_bNRNrbip41l9y55Pqaf-dJE7rX48UuvMgLcQxeTVapp4AFOfbGZl5_y3yz-AN1QmXICay22EbCgvT6YtbsJVlvK7EZy9MkazE8SZCoMHWFLFtE-nOqELH6_9GBbjLWvg0aqAehITap6ZEDwnOSa_dHWtqZYwbDoYv6GVy_3U5n7Jmu_z84NlcGPlDK7SLF3LSILmTGZZovENXxiOzavJUJH68Uuaj_d7Bibe2S44iLAnQuvoCvPZ8hVO0-RyQHaVGDM2fa25748MDijlBKR42MAXdA_S2mvJh946bMUeguwRZ2Ztu5X01F-5xBHdVtQDcyhzhqWk59WwjDLV0mMzb-uD8apVPkQU2-gfp0l-feLe7CBSgMJrs-Ox1o8a-PF_4pGOQ9SzmhQsOK7U0k_nSXzt3Fi9pFQ7sKhCrHnhT2FWO_uyKtR4Ga_dLCI0oYfmt2vgDl-s9r5ZMDh2zgX2RAegj394xglF54WOo0YFi4ruhITlt-xmts4UromsrOJ3QoUqtJCfHzLQkivdhoki2thLKfQl1s6xNJ0yjvyWvHiqJCwxGN24c8p5ZklYFykic_WuGHHrJe2T55yzOY1CzawNkd7x8UOR1i8he-Ri4I5NJz2jZ6nGdhP7XsPW3t5Wba87Y8nyc7G1D-oexqq1Fc0MEZq_G67tmIfeAAP-tw61fDQsluWUEgvVpkL3tV7sVBYLEIjm2GfYXxg7TDqWAdB00X=w401-h866-no?authuser=0)

# Side Notes
- This was my first repo: https://github.com/juancamilov06/FreshGIFs In some point the project got currupted in some way that NavComponent was not working at all. My solution was creating a new project and add all those files inside the new one. The first one, has some branching stuff and good commit history. This one not :D, I didn't want to spend my time on that but I recognize my fault.
- Nothing else, thanks for giving me the opportunity of making this test
