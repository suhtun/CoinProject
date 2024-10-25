package com.su.coinproject.mock

import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinDetail
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi

val mockCoinValue = Coin(
    id = "Qwsogvtv82FCd",
    name = "Bitcoin",
    color = "#f7931A",
    symbol = "BTC",
    price = 67482.87100039439,
    change = 0.32,
    rank = 1,
    marketCap = 1334314919354.0,
    iconUrl = "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg"
)

val mockCoinDetailValue = CoinDetail(
    description = "TON was designed in 2018 by the Durov brothers for fast transactions, and is now supported by the open TON Community.",
    websiteUrl = "https://ton.org/"
)

val mockCoinDetailResponse = """
    {
      "status": "success",
      "data": {
        "coin": {
          "uuid": "67YlI0K1b",
          "symbol": "TON",
          "name": "Toncoin",
          "description": "TON was designed in 2018 by the Durov brothers for fast transactions, and is now supported by the open TON Community.",
          "color": "#0088cc",
          "iconUrl": "https://cdn.coinranking.com/1mf2KPPah/toncoin.png",
          "websiteUrl": "https://ton.org/",
          "links": [
            {
              "name": "ton.org",
              "url": "https://ton.org/",
              "type": "website"
            },
            {
              "name": "newton-blockchain",
              "url": "https://github.com/newton-blockchain",
              "type": "github"
            },
            {
              "name": "tonblockchain",
              "url": "https://t.me/tonblockchain",
              "type": "telegram"
            }
          ],
          "supply": {
            "confirmed": true,
            "supplyAt": 1729825203,
            "max": "5000000000",
            "total": "5113818606.993701",
            "circulating": "2541804363.728677"
          },
          "numberOfMarkets": 77,
          "numberOfExchanges": 41,
          "24hVolume": "129106460",
          "marketCap": "12975709431",
          "fullyDilutedMarketCap": "25524602947",
          "price": "5.104920589497301",
          "btcPrice": "0.000074953482674803",
          "priceAt": 1729825380,
          "change": "-1.43",
          "rank": 11,
          "sparkline": [
            "5.167261456416086",
            "5.164652456221416",
            "5.174422467640068",
            "5.169062587078988",
            "5.150957907079206",
            "5.142478866843445",
            "5.113719024682915",
            "5.132132977370997",
            "5.149620730926687",
            "5.162871160819671",
            "5.171526584275691",
            "5.158178750461191",
            "5.154102519801798",
            "5.157789093337943",
            "5.16021216206451",
            "5.160943900857726",
            "5.157095576500252",
            "5.152491494757341",
            "5.1603170501437035",
            "5.138837298174626",
            "5.120585752876063",
            "5.118798930226092",
            "5.1057059320254385",
            null],
          "allTimeHigh": {
            "price": "8.274255522908753",
            "timestamp": 1718411640
          },
          "coinrankingUrl": "https://coinranking.com/coin/67YlI0K1b+toncoin-ton",
          "tier": 1,
          "lowVolume": false,
          "listedAt": 1640268459,
          "hasContent": true,
          "notices": null,
          "contractAddresses": [
            "bnb-smart-chain/0x76a797a59ba2c17726896976b7b3747bfd1d220f",
            "ethereum/0x582d872a1b094fc48f5de31d3b73f2d9be47def1"
          ],
          "tags": [
            "staking",
            "layer-1",
            "social",
            "altcoin",
            "ton"
          ]
        }
      }
    }
""".trimIndent()
val mockCoinListResponse = """
    {
  "status": "success",
  "data": {
    "stats": {
      "total": 44630,
      "totalCoins": 44630,
      "totalMarkets": 46653,
      "totalExchanges": 189,
      "totalMarketCap": "849",
      "total24hVolume": "1117540401352"
    },
    "coins": [
      {
        "uuid": "Qwsogvtv82FCd",
        "symbol": "BTC",
        "name": "Bitcoin",
        "color": "#f7931A",
        "iconUrl": "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg",
        "marketCap": "1334314919354",
        "price": "67482.87100039439",
        "listedAt": 1330214400,
        "tier": 1,
        "change": "0.32",
        "rank": 1,
        "sparkline": [
          "67196.96284516728",
          "67104.84809003533",
          "66888.5253858085",
          "66964.99452321981",
          "67155.12920820457",
          "67380.03761478668",
          "67660.21017388212",
          "67756.27221759883",
          "67773.22314024421",
          "67602.30504969755",
          "67633.62992771585",
          "67804.21539678854",
          "68016.89867667377",
          "68173.03883159132",
          "68453.8803530317",
          "68351.35518213829",
          "68169.85042743305",
          "68103.85681500517",
          "67911.71738461056",
          "67981.2573501067",
          "68103.80618217624",
          "67829.3600081368",
          "67752.0215906334",
          null],
        "lowVolume": false,
        "coinrankingUrl": "https://coinranking.com/coin/Qwsogvtv82FCd+bitcoin-btc",
        "24hVolume": "37085601599",
        "btcPrice": "1",
        "contractAddresses": []
      }
    ]
  }
}
""".trimIndent()

val mockSerchCoinsResponse = """
    {
      "status": "success",
      "data": {
        "stats": {
          "total": 176,
          "totalCoins": 44523,
          "totalMarkets": 46895,
          "totalExchanges": 190,
          "totalMarketCap": "868",
          "total24hVolume": "605458715492"
        },
        "coins": [
          {
            "uuid": "Qwsogvtv82FCd",
            "symbol": "BTC",
            "name": "Bitcoin",
            "color": "#f7931A",
            "iconUrl": "https://cdn.coinranking.com/bOabBYkcX/bitcoin_btc.svg",
            "marketCap": "1316447357302",
            "price": "66581.76619947565",
            "listedAt": 1330214400,
            "tier": 1,
            "change": "-0.50",
            "rank": 1,
            "sparkline": [
              "66966.74773845551",
              "67342.50383241825",
              "67078.9174835681",
              "67260.00982965465",
              "67406.76952976525",
              "67493.72276874137",
              "67490.17440522129",
              "67454.42080124891",
              "67670.45629731465",
              "67549.81528656678",
              "67244.16710635596",
              "67276.51848464568",
              "67212.59742315483",
              "67037.63653853795",
              "67119.50841907373",
              "67134.31681334821",
              "67165.25192560774",
              "66901.76714959905",
              "66807.85681137301",
              "66458.0319223256",
              "66532.81360188121",
              "66478.87314021781",
              "66371.47582816925",
              null],
            "lowVolume": false,
            "coinrankingUrl": "https://coinranking.com/coin/Qwsogvtv82FCd+bitcoin-btc",
            "24hVolume": "29613579066",
            "btcPrice": "1",
            "contractAddresses": []
          },
        ]
      }
    }
""".trimIndent()