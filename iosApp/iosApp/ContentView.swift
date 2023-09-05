import SwiftUI
import shared
import sharedSwift


public class SampleObservableObject: ObservableObject {
    var viewModel: SampleViewModel

    
    init(wrapper: SampleViewModel) {
        viewModel = wrapper
    }
   
}
public extension  SampleViewModel {
    func asObservableObject() -> SampleObservableObject {
        return  SampleObservableObject(wrapper: self)
    }
}

struct ContentView: View {
    @ObservedObject var viewModel = GetViewModels().getSampleViewModel().asObservableObject().viewModel
    @State var data: String = "Initial Data"
    
 var body: some View {
     Text("State: \(data)")
                 .onReceive(createPublisher(viewModel.uiState
                                           )) { state in
                     let s = UIStateKs<NSString>(state)
                     switch(s) {
                     case .data(let obj):
                        data = (obj.value as String?) ?? "nil"
                     case .empty:
                         data = "empty"
                     case .error(let obj):
                         
                         if let message=obj.throwable.message {
                             data = message
                         }else{
                             data = "Someting went wrong"
                         }
                     case .loading:
                         data = "loading"
                     }
                 }
 }
    
}
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

func processState(_ state: UIStateKs<NSString>) {
    switch(state) {
    case .data(let obj):
        print((obj.value as String?) ?? "nil")
    case .empty:
        print("empty")
    case .error(let obj):
        print(obj.throwable)
    case .loading:
        print("loading")
    }
}
