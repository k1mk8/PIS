import React from "react";
import './tabs.css'

class Tabs extends React.Component{
    state = {
        activeTab: this.props.defaultActive || 0
    };

    renderMenu() {
        return React.Children.map(this.props.children, (item, key) => {
          return (
            <button class="w3-sidebar w3-bar-block w3-light-grey"
              key={key}
              style={{
                fontWeight: key === this.state.activeTab ? "bold" : undefined
              }}
              onClick={() => this.setState({ activeTab: key })}
            >
              {item.props.title}
            </button>
          );
        });
      }

    render() {
        return (
          <div>
            <ul>{this.renderMenu()}</ul>
            <div>
              {React.Children.toArray(this.props.children)[this.state.activeTab]}
            </div>
          </div>
        );
      }
}
export default Tabs;